package com.roilka.shop.common;
import static org.junit.Assert.*;
import com.roilka.shop.common.config.SortTask;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestForkJoinSimple {

	private static final int NARRAY = 1000000; //For demo only
	long[] array = new long[NARRAY];
	Random rand = new Random();

	@Before
	public void setUp() {
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextLong()%100; //For demo only
		}
		System.out.println("Initial Array: " + Arrays.toString(array));
	}

	@Test
	public void testList(){
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < NARRAY; i++) {
			list.add(i);
		}
		long startTime = System.currentTimeMillis();
		List<Integer> subList = list.stream().filter(integer -> integer%2==0).collect(Collectors.toList());
		long endTime = System.currentTimeMillis();
		long sub = endTime-startTime;
		System.out.println("lambda处理消耗："+sub+"毫秒");
		long start2Time = System.currentTimeMillis();
		subList = new ArrayList<>();
		for (Integer a : list){
			if (a%2==0){
				subList.add(a);
			}
		}
		long end2Time = System.currentTimeMillis();
		long sub2 = endTime-startTime;
		System.out.println("foreach循环处理消耗："+sub2+"毫秒");
		//System.out.println(subList);
	}
	@Test
	public void testSort() throws Exception {
		long startTime = System.currentTimeMillis();
		ForkJoinTask sort = new SortSimpleTask(array);
		ForkJoinPool fjpool = new ForkJoinPool();
		fjpool.submit(sort);
		fjpool.shutdown();

		fjpool.awaitTermination(30, TimeUnit.SECONDS);
		long endTime = System.currentTimeMillis();
		long sub = endTime-startTime;
		System.out.println("此次处理消耗："+sub/1000+"秒");
		System.out.println("finish Array: " + Arrays.toString(array));
		assertTrue(checkSorted(array));
	}

	boolean checkSorted(long[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] >= (a[i + 1])) {
				return false;
			}
		}
		return true;
	}
}

class SortSimpleTask extends RecursiveAction {

	final long[] array;
	final int lo;
	final int hi;
	private int THRESHOLD = 30;

	public SortSimpleTask(long[] array) {
		this.array = array;
		this.lo = 0;
		this.hi = array.length - 1;
	}

	public SortSimpleTask(long[] array, int lo, int hi) {
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}

	protected void compute() {
		if (hi - lo < THRESHOLD)
			sequentiallySort(array, lo, hi);
		else {
			int pivot = partition(array, lo, hi);

			invokeAll(new com.roilka.shop.common.config.SortTask(array, lo, pivot - 1), new com.roilka.shop.common.config.SortTask(array,
					pivot + 1, hi));
		}
	}

	private int partition(long[] array, int lo, int hi) {
		long x = array[hi];
		int i = lo - 1;
		for (int j = lo; j < hi; j++) {
			if (array[j] <= x) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, hi);
		return i + 1;
	}

	private void swap(long[] array, int i, int j) {
		if (i != j) {
			long temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	private void sequentiallySort(long[] array, int lo, int hi) {
		Arrays.sort(array, lo, hi + 1);
	}


}