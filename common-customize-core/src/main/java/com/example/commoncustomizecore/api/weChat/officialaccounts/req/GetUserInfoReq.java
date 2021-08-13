package com.example.commoncustomizecore.api.weChat.officialaccounts.req;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class GetUserInfoReq
{

    /**
     * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     */
    private String access_token;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     */
    private String lang;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
