package com.hxg.api.weChat.miniprogram.req;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class GetPubTemplateTitlesReq
{
    private String access_token;

    /**
     * 类目 id，多个用逗号隔开
     */
    private String ids;

    /**
     * 用于分页，表示从 start 开始。从 0 开始计数。
     */
    private String start;

    /**
     * 用于分页，表示拉取 limit 条记录。最大为 30。
     */
    private String limit;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
