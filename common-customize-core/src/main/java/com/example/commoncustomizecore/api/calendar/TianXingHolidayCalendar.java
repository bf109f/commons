package com.example.commoncustomizecore.api.calendar;

import com.example.commoncustomizecore.api.calendar.inner.AbstractHolidayCalendar;
import com.example.commoncustomizecore.api.constants.CommonConstant;
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
public class TianXingHolidayCalendar extends AbstractHolidayCalendar
{

    private static final Logger LOG = LoggerFactory.getLogger(TianXingHolidayCalendar.class);

    /**
     * 天行api密钥
     */
    private final String key;

    /**
     * 日历起始时间
     */
    private LocalDate startDay;

    private final List<NewsInfo> newsInfos = new ArrayList<>();

    public TianXingHolidayCalendar(String key)
    {
        super(LocalDate.now());
        this.key = key;
        init();
    }

    public TianXingHolidayCalendar(String key, LocalDate startDay)
    {
        super(startDay);
        this.key = key;
        init();
    }
    public TianXingHolidayCalendar(String key, LocalDate startDay, boolean showLeap)
    {
        super(startDay, showLeap);
        this.key = key;
        init();
    }

    /**
     * 生成日历
     * @return
     */
    private void init()
    {
        AssertUtil.isBlank(key, "天行api密钥为空");

        TianApiService service = new TianApiServiceImpl();
        GetHolidaysReq req = new GetHolidaysReq();

        if (startDay == null)
            startDay = LocalDate.now();
        req.setDate(startDay.toString(CommonConstant.DATE_FORMAT_YEAR_MONTH_DAY));
        req.setType(TianApiHolidayConstants.TYPE_YEAR);
        req.setMode(TianApiHolidayConstants.MODE_ALL);
        req.setKey(key);
        GetHolidaysRsp rsp = service.getHolidays(req);
        System.out.println(rsp);
        if (CollectionUtils.isEmpty(rsp.getNewslist()))
        {
            throw new CommonsCoreException("节假日列表为空");
            /*List<String> holidays = getDays(rsp.getNewslist(), CommonConstant.HOLIDAYS);
            List<String> restDay = getDays(rsp.getNewslist(), CommonConstant.REST_DAY);*/
//            return super.generateCalendar();
        }
        this.newsInfos.addAll(rsp.getNewslist());
//        return null;
    }

    @Override
    protected List<String> getHolidays()
    {
        return getDays(this.newsInfos, CommonConstant.HOLIDAYS);
    }

    @Override
    protected List<String> getStatutoryPaydays()
    {
        List<String> list = getDays(this.newsInfos, CommonConstant.STATUTORY_PAYDAY);
        if (list.size() != CommonConstant.STATUTORY_PAY_DAYS)
        {
            LOG.error("法定记薪日: {}", list);
            throw new CommonsCoreException(
                    String.format("法定节假日天数不正确，应为%s，实际%s", CommonConstant.STATUTORY_PAY_DAYS, list.size()));
        }
        return list;
    }

    @Override
    protected List<String> getAdjustRestDays()
    {
        return getDays(this.newsInfos, CommonConstant.REST_DAY);
    }

    /**
     * 获取节假日 调休日 法定记薪日
     * @param newsInfos
     * @param type
     * @return
     */
    private List<String> getDays(List<NewsInfo> newsInfos, String type)
    {
        if (CollectionUtils.isNotEmpty(newsInfos))
        {
            List<String> list = new ArrayList<>();
            for (NewsInfo newsInfo : newsInfos)
            {
                List<String> days;
                if (CommonConstant.HOLIDAYS.equals(type))
                {
                    days = getDays(newsInfo.getVacation());
                    if (CollectionUtils.isEmpty(days))
                        throw new CommonsCoreException("节假日为空:" + type);
                } else if (CommonConstant.REST_DAY.equals(type))
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
}
