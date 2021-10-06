package com.hxg.api.calendar;

import com.hxg.api.tianapi.model.TodayInfo;

import java.util.List;

/**
 * 获取日历接口
 */
public interface HolidayCalendarService
{
    /**
     * 生成日历
     * @param year
     * @return
     */
    List<TodayInfo> getHolidayCalendar(int year);

    /**
     * 生成日历
     * @param year
     * @param showLeap
     * @return
     */
    List<TodayInfo> getHolidayCalendar(int year, boolean showLeap);
}
