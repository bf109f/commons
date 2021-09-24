package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.httputils.HttpUtils;
import com.example.commoncustomizecore.api.tianapi.TianApiService;
import com.example.commoncustomizecore.api.tianapi.constants.GetHolidayConstants;
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
        req.setType(GetHolidayConstants.TYPE_YEAR);
        req.setMode(GetHolidayConstants.MODE_ALL);
        req.setKey("");
        System.out.println(service.getHolidays(req));
    }
}
