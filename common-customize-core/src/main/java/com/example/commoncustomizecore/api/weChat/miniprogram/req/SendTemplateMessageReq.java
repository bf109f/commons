package com.example.commoncustomizecore.api.weChat.miniprogram.req;

import com.alibaba.fastjson.JSONObject;
import com.example.commoncustomizecore.api.weChat.miniprogram.model.TemplateDataInfo;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Data
public class SendTemplateMessageReq
{
    /**
     * 接收信息用户的openid
     */
    private String touser;

    /**
     * 所需下发的订阅模板id
     */
    private String template_id;

    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     */
    private String page;

    /**
     * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
     */
    private String miniprogram_state;

    /**
     * 进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
     */
    private String lang;

    /**
     * 模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     */
    private List<TemplateDataInfo> data;

    @Override
    public String toString()
    {
        JSONObject message = new JSONObject();
        message.put("touser", this.getTouser());
        message.put("template_id", this.getTemplate_id());
        if (StringUtils.isNotBlank(this.getPage()))
        {
            message.put("page", this.getPage());
        }
        if (StringUtils.isNotBlank(this.getMiniprogram_state()))
        {
            message.put("miniprogram_state", this.getMiniprogram_state());
        }
        if (StringUtils.isNotBlank(this.getLang()))
        {
            message.put("lang", this.getLang());
        }

        JSONObject detail = new JSONObject();
        if (CollectionUtils.isNotEmpty(this.getData()))
        {
            this.getData().forEach(messageData -> {
                JSONObject detailData = new JSONObject();
                detailData.put("value", messageData.getValue());
                detail.put(messageData.getName(), detailData);
            });
            message.put("data", detail);
        }

        return message.toString();
    }
}
