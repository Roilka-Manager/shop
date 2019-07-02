package com.roilka.shop.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T> List<List<T>> subList(List<T> list, int subSize) {
        List<List<T>> result = new ArrayList<>();
        if (list == null || list.size() < 1) return result;

        int orgSize = list.size();
        if (orgSize <= subSize) {
            result.add(list);
            return result;
        }

        int page = orgSize % subSize == 0 ? orgSize / subSize : orgSize / subSize + 1;
        int start = 0, end = subSize;
        for (int i = 0; i < page; i++) {
            if (i != 0) {
                start += subSize;
                end = start + subSize;
            }
            result.add(list.subList(start, end > orgSize ? orgSize : end));
        }
        return result;
    }
}