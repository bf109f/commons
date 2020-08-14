package com.example.commoncustomizecore.api.commons;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils
{
    /**
     * 获取周几
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int tmp = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (0 == tmp) {
            tmp = 7;
        }
        return tmp;
    }

    /**
     * 获取周几
     * @param dateTimeString 时间字符串 2020-08-15
     * @param pattern yyyy-MM-dd
     * @return 周几
     */
    public static int getDayOfWeek(String dateTimeString, String pattern)
    {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = dtf.parseDateTime(dateTimeString);
        return dateTime.getDayOfWeek();
    }


    /**
     * 获取周几
     * @return
     */
    public static int getDayOfWeek()
    {
        /*Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int tmp = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (0 == tmp) {
            tmp = 7;
        }*/
        DateTime dateTime = new DateTime();

        return dateTime.getDayOfWeek();
    }
}
