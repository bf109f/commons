package com.example.commoncustomizecore.api.weChat.officialaccounts.req;

import com.example.commoncustomizecore.api.weChat.officialaccounts.req.info.MessageDataInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Data
public class GetTemplateListReq
{
    /**
     * 接收消息者
     */
    private String touser;

    /**
     * 模板ID
     */
    private String template_id;

    /**
     * 模板跳转链接
     */
    private String url;

    /**
     * 消息内容
     */
    private List<MessageDataInfo> data;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
