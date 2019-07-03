package com.roilka.shop.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 时间工具类
 * 
 * @author aixiaohua
 */
public class DateUtils {

    /**
     * 比较时间
     * 
     * @param time1
     * @param time2
     * @return
     */
    public static int compareTime(Date time1, Date time2) {

        return time1.compareTo(time2);

    }

    /**
     * 比较时间是否在指定的范围内
     * 
     * @param time1 开始范围
     * @param time2 结束范围
     * @param time3 待比较的时间
     * @return
     */
    public static boolean validateTimeScope(Date time1, Date time2, Date time3) {

        boolean flag = false;

        if (time3.compareTo(time1) >= 1 && time2.compareTo(time3) >= 1) {

            flag = true;

        }

        return flag;

    }

    /**
     * 时间运算返回时间戳
     * 
     * @param time1 结束时间
     * @param time2 比较时间
     * @return
     */
    public static Long timeOperation(Date time1, Date time2) {

        return time1.getTime() - time2.getTime();

    }

    /**
     * 时间运算(默认返回秒)
     * 
     * @param time1 结束时间
     * @param time2 比较时间
     * @param number 指定的时间
     * @return
     */
    public static Long timeOperation(Date time1, Date time2, int number) {

        int num = 0;

        if (number == 0) {

            num = 1000;
        }

        return timeOperation(time1, time2) / num;

    }

    /**
     * 当前时间增加分钟
     * 
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static Date timePlus(int minutes) {

        String timeStr = LocalDateTime.now().plusMinutes(minutes)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date time1 = null;
        try {

            time1 = format.parse(timeStr);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return time1;

    }

    /**
     * 时间戳转换成Date类型
     * 
     * @param time 时间戳
     * @return
     * @throws ParseException
     */
    public static Date stampToDate(String time) {

        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(time);
        String res = simpleDateFormat.format(lt);

        try {

            date = simpleDateFormat.parse(res);

        } catch (ParseException e) {

            throw new RuntimeException("时间转换异常", e);

        }

        return date;

    }

    /**
     * 将时间转换为时间戳
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static String dateToStamp(Date date) throws ParseException {

        long ts = date.getTime();
        String res = String.valueOf(ts);
        return res;
    }

    /**
     * 时间戳转换成Date类型
     * 
     * @param time 时间戳
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String time) {
    	
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse;
        try {

            parse = simpleDateFormat.parse(time);

        } catch (ParseException e) {

            throw new RuntimeException("时间转换异常", e);

        }

        return parse;

    }

    /**
     * 时间类型转换成字符串
     * 
     * @param date
     * @return
     */
    public static String timeToString(Date date) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simple.format(date);

    }
    
    /**
     * 时间类型转换成字符串
     * 
     * @param date
     * @return
     */
    public static Date formatDate(Date date) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return stringToDate(simple.format(date) + "");

    }

    /**
     * 取得今年年度
     * 
     * @return
     */
    public static Long getThisYear() {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy");

        return new Long(simple.format(new Date()));

    }
    
    /**
     * 取得本月
     * 
     * @return
     */
    public static int getThisMonth() {

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;

        return month;

    }
    /**
     * 日期格式转换为日历格式
     * @param date
     * @return
     */
    public static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    /**
     * 取得指定日期年度
     * 
     * @return
     */
    public static Long getYear(Date date) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy");

        return new Long(simple.format(date));

    }

    /**
     * 取得去年年度
     * 
     * @return
     */
    public static Long getLastYear() {

        return getThisYear() - 1;

    }
    
    /**
     * 取得前年年度
     * 
     * @return
     */
    public static Long getYearBeforeLast() {

        return getThisYear() - 2;

    }
    
    /**
     * 取得前年年度最后一天
     * 
     * @return date
     */
    public static Date getLastDayOFYearBeforeLast() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        
        return calendar.getTime();

    }
    


    /**
     * 上个周期的第一天和最后一天
     * 
     * @param cycleType (Year:1,Month:2,QUARTER:3)
     * @return Map<String, Date>
     */
    public static Map<String, Date> getLastCycleDate(int cycleType) {

        //获取前周期的第一天
        Calendar startCalendar = Calendar.getInstance();//获取当前日期
        if (cycleType == Calendar.MONTH) {
            startCalendar.add(Calendar.MONTH, -1);
        }else if(cycleType == Calendar.YEAR) {
            startCalendar.add(Calendar.MONTH, -12);
        }else if(cycleType == 3) {
            startCalendar.add(Calendar.MONTH, -3);
        }else{
            //默认为上月的第一天
            startCalendar.add(Calendar.MONTH, -1);
        }
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);

        //获取前周期的最后一天
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.DAY_OF_MONTH, 0);
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);

        Map<String, Date> map = new HashMap<String, Date>();
        map.put("startDate", startCalendar.getTime());
        map.put("endDate", endCalendar.getTime());

        return map;
    }
    
    /**
     * 获取开始时间
     * 
     * @param start
     * @param end
     * @return
     */
    public static Date startDateByDay(Date start, int end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DATE, end);// 明天1，昨天-1
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取结束时间
     * 
     * @param date
     * @return
     */
    public static Date endDateByDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    
    /**
     * 获取指定日期的前一天开始时间
     * 
     * @param date
     * @return
     */
    public static Date getPreDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * 获取指定日期的前一天结束时间
     * 
     * @param date
     * @return
     */
    public static Date getPreDayLastTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
 
    /**
     * 获取上月最后一天
     * 
     * 
     * @param start
     * @param end
     * @return
     */
    public static Date getLastMonthLastDay() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        
        return calendar.getTime();
    }
    
    /**
     * 获取上月第一天
     * 
     * @return Date     * 
     */
    public static Date getLastMonthStartDay() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        return calendar.getTime();
    }
    
    /**
     * 获取年最后一天
     * 
     * @param start
     * @param end
     * @return
     */
    public static Date getYearLastDay(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date yearLast = calendar.getTime();
         
        return yearLast;
    }
    

    
    /** 
     * 得到几天前的时间 
     * @param date 
     * @param day 
     * @return 
     */  
    public static Date getDateBefore(Date date,int day){  
     Calendar now =Calendar.getInstance();  
     now.setTime(date);  
     now.set(Calendar.DATE,now.get(Calendar.DATE)-day);  
     return now.getTime();  
    }  
      
    /** 
     * 得到几天后的时间 
     * @param date 
     * @param day 
     * @return 
     */  
    public static Date getDateAfter(Date date,int day){  
     Calendar now =Calendar.getInstance();  
     now.setTime(date);  
     now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
     return now.getTime();  
    } 
    
    /**
     * 
     * 
     * @param time 时间戳
     * @return
     * @throws ParseException
     */
    public static String timeToString(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(time);
    }
    
    /**
     * 时间类型转换成字符串
     * 
     * @param date
     * @return
     */
    public static String timeToString(Object date) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simple.format(date);

    }
    
    /**
     * 时间类型转换成字符串
     * 
     * @param date
     * @return
     */
    public static String dateToString(Date date) {

        SimpleDateFormat simple = new SimpleDateFormat("yyMMdd");

        return simple.format(date);

    }
    /**
     * 时间类型转换成字符串(yy-MM-dd HH:mm:ss)
     * 
     * @param date
     * @return
     */
    public static String datesToStringAll(Date date) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simple.format(date);

    }
    /**
     * 时间类型转换成字符串(yy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String datesFormatToStringAll(Date date) {
        if (date == null){
            return null;
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simple.format(date);

    }
    /**
     * 时间类型转换成字符串(yy-MM-dd)
     * 
     * @param date
     * @return
     */
    public static String datesToString(Date date) {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");

        return simple.format(date);

    }
    /**
     * 时间增加一年
     * 
     * @param date
     * @return
     */
    public static Date addNYear(Date date,int n) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        cal.add(Calendar.YEAR, n);//增加N年

        return cal.getTime();
    }
    
    /**
     * 时间戳转换成Date类型
     * 
     * @param time 时间戳
     * @return
     * @throws ParseException
     */
    public static Date stringFormatToDate(String time) {
    	
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date parse;
        try {

            parse = simpleDateFormat.parse(time);

        } catch (ParseException e) {

            throw new RuntimeException("时间转换异常", e);

        }

        return parse;

    }
    /**
     * 时间戳转换成Date类型（无格式）
     * 
     * @param time 时间戳
     * @return
     * @throws ParseException
     */
    public static Date stringFormatToDateN(String time) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date parse;
        try {

            parse = simpleDateFormat.parse(time);

        } catch (ParseException e) {

            throw new RuntimeException("时间转换异常", e);

        }

        return parse;

    }
    /**
     * 时间戳转换成Date类型（"-"）
     * 
     * @param time 时间戳
     * @return
     * @throws ParseException
     */
    public static Date stringFormatToDateL(String time) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date parse;
        try {

            parse = simpleDateFormat.parse(time);

        } catch (ParseException e) {

            throw new RuntimeException("时间转换异常", e);

        }

        return parse;

    }
    /**
     * 时间戳转换成Date类型(仅年月日)
     * 
     * @param time 时间戳
     * @return
     * @throws ParseException
     */
    public static Date stringFormatToDates(String time) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date parse;
        try {

            parse = simpleDateFormat.parse(time);

        } catch (ParseException e) {

            throw new RuntimeException("时间转换异常", e);

        }

        return parse;

    }
    
    /** 
     * 得到前天的时间 
     * @param date 
     * @return 
     */  
    public static String getDateYYYYMMDD(Date date){  
    	return new SimpleDateFormat("yyyy-MM-dd").format(date);  
    }
    /** 
     * 得到字符串日期+1天
     * @param date 
     * @return 
     */ 
	public static String getDateYYYYMMDDAddDay(String date){
    	try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.DATE,1);
			now =cal.getTime();
			return (new SimpleDateFormat("yyyy-MM-dd")).format(now);
		} catch (ParseException e) {
			return date;
		}
    }
	/**
	 * 取月份
	 * @param date
	 * @return
	 */
	public static int getDateMM(String date){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(sdf.parse(date));
			return (calendar.get(Calendar.MONTH) + 1);
		} catch (ParseException e) {
			return 0;
		}
	} 
    /** 
     * 得到几天前的时间 
     * @param date 
     * @param day 
     * @return 
     */  
    public static String getDateBeforeYYYYMMDD(Date date,int day){  
    	Calendar now =Calendar.getInstance();  
    	now.setTime(date);  
    	now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
    	return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());  
    }  
    
    /**
     * 遍历时段内的日期（yyyy-MM-dd）
     * @param startDate
     * @param finishDate
     * @return
     * @throws ParseException
     */
    public static List<String> getAllDateBetween(String startDate,String finishDate) throws ParseException {
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        
        //开始时间必须小于结束时间
        List<String> list = new ArrayList<String>();
        Date beginDate = dateFormat1.parse(startDate);
        Date endDate = dateFormat1.parse(finishDate);
        if (beginDate.compareTo(endDate) > 0) {
            return null;
        }
        Date date = beginDate;
        while (date.compareTo(endDate) <= 0) {
        list.add(DateUtils.datesToString(date));
        c.setTime(date);
        c.add(Calendar.DATE, 1); // 日期加1天
        date = c.getTime();
        }
        return list;
    }

    /**
     * 按照指定格式转换指定时间
     * @param date
     * @param format
     * @return
     */
    public static String timeToStringByFormat(Date date,String format){
        String parse;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            parse = simpleDateFormat.format(date);
        } catch (Exception e) {
            throw new RuntimeException("时间转换异常", e);
        }
        return parse;
    }
    
    public static Date stringToDateByYYYYMMDD(String time) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
        	date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException("时间转换异常", e);
        }
        return date;
    }
    public static void main(String[] args) throws ParseException{
        System.out.println(stampToDate(new Date().getTime() + ""));
        System.out.println(getLastDayOFYearBeforeLast());
        System.out.println(23*100/50);
        String clazzName4 = Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().lastIndexOf(".")+1);  
        System.out.println(clazzName4);  
    }

}
