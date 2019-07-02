package com.roilka.shop.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhanghui
 * @description 日期常用工具类
 * @date 2019/1/23
 */
public class UserDateUtils {

    public static void main(String[] args) {

        System.out.println(addDate(new Date(),1,Calendar.MONTH));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data = "2019-01-30";
        Date result = null;
        try {
            result= addDate(simpleDateFormat.parse(data),1,Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("我是结果："+simpleDateFormat.format(result));
    }

    /**
     *
     * @param date  待处理日期
     * @param sum   加减数量，仅为整数，可为负数
     * @param type  例如 ：Calendar.YEAR : 1 （年）
     * @return
     */
    public static Date addDate(Date date,int sum,int type){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, sum);
        return cal.getTime();
    }
}
