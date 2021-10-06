package com.hxg.api.tianapi;

import com.hxg.api.exception.CommonsCoreException;
import com.hxg.api.tianapi.req.GetHolidaysReq;
import com.hxg.api.tianapi.rsp.GetHolidaysRsp;

public interface TianApiService
{
    /**
     * 获取节假日
     * @param req
     * @return
     * @throws CommonsCoreException
     */
    GetHolidaysRsp getHolidays(GetHolidaysReq req) throws CommonsCoreException;
}
