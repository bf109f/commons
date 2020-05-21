package com.example.commoncustomizecore.api.weChat.publicaccount.req;

import com.alibaba.fastjson.JSONObject;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.info.MessageDataInfo;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
public class SendTemplateMessageReq
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
        JSONObject message = new JSONObject();
        message.put("touser", this.getTouser());
        message.put("template_id", this.getTemplate_id());
        message.put("url", this.getUrl());
        JSONObject detail = new JSONObject();
        if (CollectionUtils.isNotEmpty(this.getData()))
        {
            this.getData().forEach(messageData -> {
                JSONObject detailData = new JSONObject();
                detailData.put("value", messageData.getValue());
                if (StringUtils.isNotBlank(messageData.getColor()))
                {
                    detailData.put("color", messageData.getColor());
                }

                detail.put(messageData.getName(), detailData);
            });
            message.put("data", detail);
        }

        return message.toString();
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
