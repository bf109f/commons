package com.example.commoncustomizecore.api.weChat.publicaccount.req;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class GetTokenReq
{
    /**
     * 获取access_token填写client_credential
     */
    private String grant_type = "client_credential";

    /**
     * appId
     */
    private String appid;

    /**
     * appSecret
     */
    private String secret;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
