package com.example.commoncustomizecore.api.weChat.miniprogram.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 类目信息
 */
@Data
public class CategoryInfo
{
    /**
     * 类目ID
     */
    private String id;

    /**
     * 类目名称
     */
    private String name;


    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
