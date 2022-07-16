package com.hxg.sms.api;

import com.hxg.sms.exception.SmsException;
import com.hxg.sms.model.req.SmsBaseReq;
import com.hxg.sms.model.rsp.SmsBaseRsp;

/**
 * 短信服务
 * @param <P>
 * @param <Q>
 */
public interface SmsApiService<P extends SmsBaseRsp, Q extends SmsBaseReq>
{
    /**
     * 发送短信
     * @param req 请求
     * @return 响应
     * @throws SmsException
     */
    P sendSms(Q req) throws SmsException;

    /**
     * 批量发送短信
     * @param req
     * @return
     * @throws SmsException
     */
    P batchSendSms(Q req) throws SmsException;

    /**
     * 查询
     * @param req
     * @return
     * @throws SmsException
     */
    P querySms(Q req) throws SmsException;

    /**
     * 查询短信余额
     * @param req
     * @return
     * @throws SmsException
     */
    P querySmsBalance(Q req) throws SmsException;
}
