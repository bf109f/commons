package com.hxg.api.weChat;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class WXBaseRsp
{
    /**
     * 响应码
     */
    private Integer errcode;

    /**
     * 响应信息
     */
    private String errmsg;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
