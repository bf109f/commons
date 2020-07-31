package com.example.commoncustomizecore.api.weChat.miniprogram.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 模板消息数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDataInfo
{
    /**
     * 数据key值
     */
    private String name;

    /**
     * 数据值
     */
    private String value;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
