package com.example.commoncustomizecore.api.tianapi.impl;

import com.alibaba.fastjson.JSON;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.httputils.HttpUtils;
import com.example.commoncustomizecore.api.tianapi.TianApiService;
import com.example.commoncustomizecore.api.tianapi.req.GetHolidaysReq;
import com.example.commoncustomizecore.api.tianapi.rsp.GetHolidaysRsp;

public class TianApiServiceImpl implements TianApiService
{
    @Override
    public GetHolidaysRsp getHolidays(GetHolidaysReq req) throws CommonsCoreException
    {
        String rsp = HttpUtils.sendGet(req, "https://api.tianapi.com/txapi/jiejiari/index");
        return JSON.parseObject(rsp, GetHolidaysRsp.class);
    }
}
