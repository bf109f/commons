package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.commons.CommonCoreUtils;
import com.example.commoncustomizecore.api.httputils.HttpUtils;
import com.example.commoncustomizecore.api.tianapi.TianApiService;
import com.example.commoncustomizecore.api.tianapi.constants.TianApiHolidayConstants;
import com.example.commoncustomizecore.api.tianapi.impl.TianApiServiceImpl;
import com.example.commoncustomizecore.api.tianapi.req.GetHolidaysReq;
import org.junit.Test;

public class HttpTest
{
    @Test
    public void test()
    {
        HttpUtils.sendGet("https://baidu.com");
    }

    @Test
    public void testGetHolidays()
    {
        TianApiService service = new TianApiServiceImpl();
        GetHolidaysReq req = new GetHolidaysReq();
        req.setDate("2021-01-01");
        req.setType(TianApiHolidayConstants.TYPE_YEAR);
        req.setMode(TianApiHolidayConstants.MODE_ALL);
        req.setKey("");
        System.out.println(service.getHolidays(req));
    }

    @Test
    public void testGetCalendar()
    {
        System.out.println(CommonCoreUtils.getCalendar(""));;
    }
}
