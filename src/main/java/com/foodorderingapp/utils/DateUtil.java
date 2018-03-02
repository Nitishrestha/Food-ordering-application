package com.foodorderingapp.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by TOPSHI KREATS on 3/2/2018.
 */
public class DateUtil {
    public static Date getTomorrowDate(){
        Calendar c = Calendar.getInstance();
     //   calendar.setTime();
     //   calendar.set(Calendar.HOUR,0);
     //   calendar.set(Calendar.MINUTE,0);
     //   calendar.set(Calendar.SECOND,0);
      //  calendar.set(Calendar.DATE,Calendar.);
     //   return calendar.getTime();
        //c.add(Calendar.YEAR, 1);
       // c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.AM_PM,Calendar.AM);
        return c.getTime();
    }

    public static Date getTodayDate(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.AM_PM,Calendar.AM);
        return c.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getTomorrowDate());
        System.out.println(getTodayDate());
    }
}
