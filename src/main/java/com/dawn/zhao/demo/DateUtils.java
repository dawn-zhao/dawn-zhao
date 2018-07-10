package com.dawn.zhao.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static void main(String[] args) {
//        TimeUnit unit = TimeUnit.MINUTES;
//        int rangeEnd = 0;
//        int rangeBegin = -5;
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        if (!unit.equals(TimeUnit.SECONDS)) {
//            calendar.set(Calendar.SECOND, 0);
//        }
//        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.add(Calendar.SECOND, ((Long) unit.toSeconds(rangeEnd)).intValue());
//        Date endTime = calendar.getTime();
//        int endTimeMonth = calendar.get(Calendar.MONTH);
//        calendar.add(Calendar.SECOND, ((Long) unit.toSeconds(rangeBegin - rangeEnd)).intValue());
//        Date beginTime = calendar.getTime();
//        int beginTimeMonth = calendar.get(Calendar.MONTH);
//
//        System.out.println(beginTime);
//        System.out.println(endTime);
        Calendar calendar = Calendar.getInstance();
        Date endTime = new Date();
        calendar.setTime(endTime);
        int endMonth = calendar.get(Calendar.MONTH);
        calendar.add(Calendar.SECOND, ((Long) TimeUnit.MINUTES.toSeconds(-5)).intValue());
        Date beginTime = calendar.getTime();
        int beginMonth = calendar.get(Calendar.MONTH);

        System.out.println("开始月份:"+beginMonth+",开始时间:"+beginTime);
        System.out.println("结束月份:"+endMonth+",结束时间:"+endTime);


        calendar.setTime(endTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime());

//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTime(new Date());
//        calendar2.set(Calendar.MILLISECOND, 0);
//        calendar2.add(Calendar.SECOND, ((Long) unit.toSeconds(rangeBegin - rangeEnd)).intValue());
//        calendar2.setTime(calendar2.getTime());
//        System.out.println(calendar2.get);

    }

}
