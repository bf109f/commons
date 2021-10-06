package com.hxg.api.weChat.officialaccounts.rsp;

import com.hxg.api.weChat.WXBaseRsp;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
@Data
public class GetTokenRsp extends WXBaseRsp
{
    private String access_token;

    private long expires_in;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
