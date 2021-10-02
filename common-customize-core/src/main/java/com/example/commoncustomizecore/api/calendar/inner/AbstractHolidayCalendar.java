package com.example.commoncustomizecore.api.calendar.inner;

import com.example.commoncustomizecore.api.constants.CommonConstant;
import com.example.commoncustomizecore.api.tianapi.constants.TianApiHolidayConstants;
import com.example.commoncustomizecore.api.tianapi.model.TodayInfo;
import com.github.heqiao2010.lunar.LunarCalendar;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.*;

public abstract class AbstractHolidayCalendar
{
    /**
     * 是否展示闰年
     */
    private boolean showLeap = true;

    /**
     * 日历的起始日期
     */
    private final LocalDate startDay;

    protected AbstractHolidayCalendar()
    {
        this.startDay = LocalDate.now();
    }

    protected AbstractHolidayCalendar(LocalDate startDay)
    {
        this.startDay = startDay;
    }

    protected AbstractHolidayCalendar(LocalDate startDay, boolean showLeap)
    {
        this.showLeap = showLeap;
        this.startDay = startDay;
    }

    /*private void init()
    {
        statutoryHolidays = new ArrayList<>();
        statutoryHolidays.add("01-01");
        statutoryHolidays.add("腊月三十");
        statutoryHolidays.add("正月初一");
        statutoryHolidays.add("正月初二");
        statutoryHolidays.add("正月初三");
        statutoryHolidays.add("五月初五");
        statutoryHolidays.add("05-01");
        statutoryHolidays.add("10-01");
        statutoryHolidays.add("10-02");
        statutoryHolidays.add("10-03");
    }*/

    /**
     * 节假日列表
     * @return
     */
    protected abstract List<String> getHolidays();

    /**
     * 法定记薪日
     * @return
     */
    protected abstract List<String> getStatutoryPaydays();

    /**
     * 调休日
     * @return
     */
    protected abstract List<String> getAdjustRestDays();

    /**
     * 生成日历
     * @return
     */
    public List<TodayInfo> generateCalendar()
    {
        /**
         * 法定记薪日
         */
        List<String> statutoryPaydays = getStatutoryPaydays();
        /**
         * 节假日列表
         */
        List<String> holidays = getHolidays();
        /**
         * 调休日
         */
        List<String> adjustRestDays = getAdjustRestDays();

        int thisYear = startDay.getYear();
        LocalDate localDate = startDay;

        List<TodayInfo> list = new LinkedList<>();
        for (; localDate.getYear() == thisYear; localDate = localDate.plusDays(1))
        {
            String date = localDate.toString(CommonConstant.DATE_FORMAT_YEAR_MONTH_DAY);
            TodayInfo info = new TodayInfo();
            info.setDate(date);
            info.setWeekday(localDate.getDayOfWeek());

            LunarCalendar calendar = LunarCalendar.solar2Lunar(localDate.toDateTimeAtCurrentTime().toGregorianCalendar());
            info.setLunarYearMonthDay(calendar.toString());
            info.setLunarMonthDay(calendar.getLunar(showLeap));
            if (CollectionUtils.isNotEmpty(holidays) && holidays.contains(date))
            {
                // 节假日
                info.setDayDesc(CommonConstant.HOLIDAYS);
                info.setDayCode(TianApiHolidayConstants.HOLIDAYS);
                if (statutoryPaydays.contains(date) || statutoryPaydays.contains(info.getLunarMonthDay()))
                {
                    info.setWhetherPaid(CommonConstant.PAY);
                } else
                {
                    info.setWhetherPaid(CommonConstant.NOT_PAY);
                }
            } else if (CollectionUtils.isNotEmpty(adjustRestDays) && adjustRestDays.contains(date))
            {
                // 调休日 上班
                info.setDayDesc(CommonConstant.REST_DAY);
                info.setDayCode(TianApiHolidayConstants.REST_DAY);
                info.setWhetherPaid(CommonConstant.PAY);
            } else if (localDate.getDayOfWeek() == DateTimeConstants.SATURDAY ||
                    localDate.getDayOfWeek() == DateTimeConstants.SUNDAY)
            {
                // 周末
                info.setDayDesc(CommonConstant.HOLIDAYS);
                info.setDayCode(TianApiHolidayConstants.WEEKEND);
                info.setWhetherPaid(CommonConstant.NOT_PAY);
            } else
            {
                // 工作日
                info.setDayDesc(CommonConstant.REST_DAY);
                info.setDayCode(TianApiHolidayConstants.WORKING_DAY);
                info.setWhetherPaid(CommonConstant.PAY);
            }
            list.add(info);
        }
        return list;
    }


    protected List<String> getDays(String days)
    {
        if (StringUtils.isNotBlank(days))
        {
            if (days.contains("|"))
            {
                String [] arr = days.split("\\|");
                return Arrays.asList(arr);
            }
            return Collections.singletonList(days);
        }
        return null;
    }
}
