package com.example.commoncustomizecore.api.commons;

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
     * @return
     */
    public static int getDayOfWeek()
    {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int tmp = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (0 == tmp) {
            tmp = 7;
        }
        return tmp;
    }
}
