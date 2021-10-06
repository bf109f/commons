package com.hxg.api.tianapi.impl;

import com.alibaba.fastjson.JSON;
import com.hxg.api.exception.CommonsCoreException;
import com.hxg.api.httputils.HttpUtils;
import com.hxg.api.tianapi.TianApiService;
import com.hxg.api.tianapi.req.GetHolidaysReq;
import com.hxg.api.tianapi.rsp.GetHolidaysRsp;

public class TianApiServiceImpl implements TianApiService
{
    @Override
    public GetHolidaysRsp getHolidays(GetHolidaysReq req) throws CommonsCoreException
    {
        String rsp = HttpUtils.sendGet(req, "https://api.tianapi.com/txapi/jiejiari/index");
        return JSON.parseObject(rsp, GetHolidaysRsp.class);
    }
}
