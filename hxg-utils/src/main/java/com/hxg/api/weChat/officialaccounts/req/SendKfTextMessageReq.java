package com.hxg.api.weChat.officialaccounts.req;

import com.hxg.api.weChat.officialaccounts.req.info.TextInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class SendKfTextMessageReq
{
    /**
     * 接收消息者
     */
    private String touser;

    /**
     * 消息类型
     */
    private String msgtype = "text";

    /**
     * 消息内容
     */
    private TextInfo text;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
