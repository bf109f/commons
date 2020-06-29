package com.example.commoncustomizecore.api.weChat.officialaccounts.rsp;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
@Data
public class GetTokenRsp
{
    private String access_token;

    private long expires_in;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
