package com.hxg.sms.supplier.gyytz.model;

import com.hxg.core.annotation.CheckRequestHeader;
import com.hxg.sms.model.req.SmsBaseReq;
import org.apache.http.HttpHeaders;

@CheckRequestHeader(mustHeaders = {HttpHeaders.CONTENT_TYPE}, length = 1)
public class SendReq extends SmsBaseReq
{

}
