package com.hxg.api.weChat.miniprogram.rsp;

import com.hxg.api.weChat.WXBaseRsp;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class SendMiniTemplateMessageRsp extends WXBaseRsp
{
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
