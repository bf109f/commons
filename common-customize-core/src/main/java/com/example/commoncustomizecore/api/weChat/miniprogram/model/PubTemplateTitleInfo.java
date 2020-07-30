package com.example.commoncustomizecore.api.weChat.miniprogram.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 类目信息
 */
@Data
public class PubTemplateTitleInfo
{
    /**
     * 模版标题 id
     */
    private String tid;

    /**
     * 模版标题
     */
    private String title;

    /**
     * 模版类型，2 为一次性订阅，3 为长期订阅
     */
    private String type;

    /**
     * 模版所属类目 id
     */
    private String categoryId;


    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
