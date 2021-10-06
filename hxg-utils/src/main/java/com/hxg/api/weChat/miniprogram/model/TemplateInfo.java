package com.hxg.api.weChat.miniprogram.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 模板信息
 */
@Data
public class TemplateInfo
{
    /**
     * 添加至帐号下的模板 id，发送小程序订阅消息时所需
     */
    private String priTmplId;

    /**
     * 模版标题
     */
    private String title;

    /**
     * 模版内容
     */
    private String content;

    /**
     * 模板内容示例
     */
    private String example;

    /**
     * 模版类型，2 为一次性订阅，3 为长期订阅
     */
    private String type;


    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
