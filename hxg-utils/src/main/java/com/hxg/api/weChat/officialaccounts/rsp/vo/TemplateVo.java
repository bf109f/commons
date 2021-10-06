package com.hxg.api.weChat.officialaccounts.rsp.vo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class TemplateVo
{
    private String template_id;

    private String title;

    private String primary_industry;

    private String deputy_industry;

    private String content;

    private String example;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
