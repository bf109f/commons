package com.example.commoncustomizecore.api.weChat.miniprogram.rsp;

import com.example.commoncustomizecore.api.weChat.WXBaseRsp;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class SendTemplateMessageRsp extends WXBaseRsp
{
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
