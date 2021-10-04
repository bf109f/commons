package com.example.commoncustomizecore.api.calendar;

import com.example.commoncustomizecore.api.calendar.inner.AbstractHolidayCalendar;
import com.example.commoncustomizecore.api.calendar.inner.HolidayCalendarCacheInfo;
import com.example.commoncustomizecore.api.constants.DateConstant;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.tianapi.TianApiService;
import com.example.commoncustomizecore.api.tianapi.constants.TianApiHolidayConstants;
import com.example.commoncustomizecore.api.tianapi.impl.TianApiServiceImpl;
import com.example.commoncustomizecore.api.tianapi.model.NewsInfo;
import com.example.commoncustomizecore.api.tianapi.req.GetHolidaysReq;
import com.example.commoncustomizecore.api.tianapi.rsp.GetHolidaysRsp;
import com.example.commoncustomizecore.api.utils.AssertUtil;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 天行接口生成日历
 */
public class TianXingHolidayCalendarSingleton extends AbstractHolidayCalendar
{
    private static final Logger LOG = LoggerFactory.getLogger(TianXingHolidayCalendarSingleton.class);

    private static volatile TianXingHolidayCalendarSingleton instance;

    /**
     * 天行api密钥
     */
    private String key;

    private volatile Long cacheTime = 24*60*60*1000L;

    /**
     * 缓存结果
     */
    private static final Map<String, HolidayCalendarCacheInfo> cache = new ConcurrentHashMap<>();

    public static TianXingHolidayCalendarSingleton getInstance(String key)
    {
        if (instance == null)
        {
            synchronized (TianXingHolidayCalendarSingleton.class)
            {
                if (instance == null)
                {
                    instance = new TianXingHolidayCalendarSingleton(key);
                }
            }
        }
        return instance;
    }

    private TianXingHolidayCalendarSingleton(String key)
    {
        this.key = key;
    }

    private synchronized List<NewsInfo> getTianXingHolidays(int year)
    {
        if (cache.get(String.valueOf(year)) == null ||
                (System.currentTimeMillis() - cache.get(String.valueOf(year)).getSaveTime() > cacheTime))
        {
            AssertUtil.isBlank(key, "天行api密钥为空");
            TianApiService service = new TianApiServiceImpl();
            GetHolidaysReq req = new GetHolidaysReq();
            req.setDate(LocalDate.now().toString(DateConstant.DATE_FORMAT_YEAR_MONTH_DAY));
            req.setType(TianApiHolidayConstants.TYPE_YEAR);
            req.setMode(TianApiHolidayConstants.MODE_ALL);
            req.setKey(key);
            GetHolidaysRsp rsp = service.getHolidays(req);
            if (CollectionUtils.isEmpty(rsp.getNewslist()))
                throw new CommonsCoreException("节假日列表为空");

            HolidayCalendarCacheInfo info = new HolidayCalendarCacheInfo();
            info.setInfos(rsp.getNewslist());
            info.setSaveTime(System.currentTimeMillis());
            cache.put(String.valueOf(year), info);

            return rsp.getNewslist();
        }
        return cache.get(String.valueOf(year)).getInfos();
    }

    @Override
    protected List<String> getHolidays(int year)
    {
        return getDays(year, DateConstant.HOLIDAYS);
    }

    @Override
    protected List<String> getStatutoryPaydays(int year)
    {
        List<String> list =
                getDays(year, DateConstant.STATUTORY_PAYDAY);
        if (CollectionUtils.isEmpty(list))
        {
            LOG.error("法定记薪日: {}", list);
            throw new CommonsCoreException(
                    String.format("法定节假日天数不正确，应为%s，实际%s", DateConstant.STATUTORY_PAY_DAYS, null));
        } else if (list.size() != DateConstant.STATUTORY_PAY_DAYS)
        {
            LOG.error("法定记薪日: {}", list);
            throw new CommonsCoreException(
                    String.format("法定节假日天数不正确，应为%s，实际%s", DateConstant.STATUTORY_PAY_DAYS,
                            list.size()));
        }
        return list;
    }

    @Override
    protected List<String> getAdjustRestDays(int year)
    {
        return getDays(year, DateConstant.REST_DAY);
    }

    /**
     * 获取节假日 调休日 法定记薪日
     * @param year 需要生成日历的年份
     * @param type 日期类型
     * @return
     */
    private List<String> getDays(int year, String type)
    {
        List<NewsInfo> newsInfos = getTianXingHolidays(year);
        if (CollectionUtils.isNotEmpty(newsInfos))
        {
            List<String> list = new ArrayList<>();
            for (NewsInfo newsInfo : newsInfos)
            {
                List<String> days;
                if (DateConstant.HOLIDAYS.equals(type))
                {
                    days = getDays(newsInfo.getVacation());
                    if (CollectionUtils.isEmpty(days))
                        throw new CommonsCoreException("节假日为空:" + type);
                } else if (DateConstant.REST_DAY.equals(type))
                {
                    days = getDays(newsInfo.getRemark());
                } else
                {
                    days = getDays(newsInfo.getWage());
                }
                if (CollectionUtils.isNotEmpty(days))
                    list.addAll(days);

            }
            return list;
        }
        return null;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public Long getCacheTime()
    {
        return cacheTime;
    }

    public void setCacheTime(Long cacheTime)
    {
        this.cacheTime = cacheTime;
    }
}
