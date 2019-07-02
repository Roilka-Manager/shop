
package com.roilka.shop.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sunyi1
 * @date 2018/10/22 15:20
 */
@Slf4j
public class DataUtil {

	private DataUtil() {
	}

	/**
	 * 判断EmptyOrNull
	 */
	public static Boolean isEmptyOrNull(Object... objs) {
		Boolean flag = false;
		for (Object obj : objs) {
			if (flag) {
				break;
			} else if (obj instanceof String) {
				flag = "".equals(obj);
			} else {
				flag = obj == null;
			}
		}
		return flag;
	}

	/**
	 * int类型转百分比
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static String intToPersent(int a, int b) {
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat.format((float) a / (float) b * 100) + "%";
	}

	public static String markPhoneNo(String phoneNo){
		return phoneNo == null || phoneNo.length() < 11 ? phoneNo :
				phoneNo.substring(0, 3) + "****" + phoneNo.substring(phoneNo.length() - 4);
	}
	public static String markIdNumber(String idNumber){
		return idNumber == null || idNumber.length() < 18 ? idNumber :
				idNumber.substring(0, 3) + "************" + idNumber.substring(idNumber.length() - 3);
	}
	public static void main(String[] args) {
		System.out.println(intToPersent(3, 6));
	}
}
