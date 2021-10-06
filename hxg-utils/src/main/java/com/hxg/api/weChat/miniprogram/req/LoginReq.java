package com.hxg.api.weChat.miniprogram.req;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class LoginReq
{
    /**
     * appid
     */
    private String appid;

    /**
     * appSecret
     */
    private String secret;

    /**
     * 登录时获取的 code
     */
    private String js_code;

    /**
     * 授权类型，此处只需填写 authorization_code
     */
    private String grant_type = "authorization_code";

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
