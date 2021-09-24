package com.example.commoncustomizecore.api.tianapi.rsp;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class TianApiBaseRsp
{
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回消息
     */
    private String msg;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
