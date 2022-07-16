package com.hxg.sms.model.req;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.Header;

import java.io.Serializable;
import java.util.List;

/**
 * 短信请求
 */
public class SmsBaseReq implements Serializable
{
    /**
     * http请求头
     */
    @Getter
    @Setter
    private List<Header> headers;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
