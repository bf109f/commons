package com.example.commoncustomizecore.api.tianapi;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.tianapi.req.GetHolidaysReq;
import com.example.commoncustomizecore.api.tianapi.rsp.GetHolidaysRsp;

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
