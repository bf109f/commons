package com.hxg.sms.model.rsp;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 短信响应
 */

public class SmsBaseRsp implements Serializable
{
    /**
     * 响应码
     */
    @Getter
    @Setter
    private String code;

    /**
     * 响应信息
     */
    @Getter
    @Setter
    private String message;

    /**
     * 响应内容
     */
    @Getter
    @Setter
    private String content;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
