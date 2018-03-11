package com.dawn.zhao.dateutils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //开始时间 前一日上午6点
    public static Date getBeginTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if(dayOfMonth==1){
            calendar.add(Calendar.MONTH, -1);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    //结束时间 上午6点
    public static Date getEndTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    public static void main(String[] args) {
        System.out.println(ymdhms.format(getBeginTime(new Date())));
        System.out.println(ymdhms.format(getEndTime(new Date())));
    }
}
