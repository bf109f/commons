package com.example.commoncustomizecore.api.calendar.inner;

import com.example.commoncustomizecore.api.calendar.HolidayCalendarService;
import com.example.commoncustomizecore.api.constants.CommonConstant;
import com.example.commoncustomizecore.api.tianapi.constants.TianApiHolidayConstants;
import com.example.commoncustomizecore.api.tianapi.model.TodayInfo;
import com.github.heqiao2010.lunar.LunarCalendar;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 日历生成接口实现类基类
 */
public abstract class AbstractHolidayCalendar implements HolidayCalendarService
{
    /**
     * 节假日列表
     * @param year 需要生成日历的年份
     * @return
     */
    protected abstract List<String> getHolidays(int year);

    /**
     * 法定记薪日
     * @param year 需要生成日历的年份
     * @return
     */
    protected abstract List<String> getStatutoryPaydays(int year);

    /**
     * 调休日
     * @param year 需要生成日历的年份
     * @return
     */
    protected abstract List<String> getAdjustRestDays(int year);

    /**
     * 生成日历
     * @return
     */
    @Override
    public List<TodayInfo> getHolidayCalendar(int year)
    {
        return getHolidayCalendar(year, false);
    }

    @Override
    public List<TodayInfo> getHolidayCalendar(int year, boolean showLeap)
    {
        long start = System.currentTimeMillis();
        // 法定记薪日
        List<String> statutoryPaydays = getStatutoryPaydays(year);

        // 节假日列表
        List<String> holidays = getHolidays(year);

        // 调休日
        List<String> adjustRestDays = getAdjustRestDays(year);

//        int thisYear = startDay.getYear();
        LocalDate localDate = new LocalDate(year, 1, 1);

        List<TodayInfo> list = new LinkedList<>();
        for (; localDate.getYear() == year; localDate = localDate.plusDays(1))
        {
            String date = localDate.toString(CommonConstant.DATE_FORMAT_YEAR_MONTH_DAY);
            TodayInfo info = new TodayInfo();
            info.setDate(localDate);
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
        long end = System.currentTimeMillis();
        System.out.printf("线程[%s]耗时[%d]ms%n", Thread.currentThread().getName(), end - start);
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
