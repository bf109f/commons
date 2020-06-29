package com.example.commoncustomizecore.api.weChat.officialaccounts.req;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class AddKfReq
{
    /**
     * 客服帐号 test1@test
     */
    private String kf_account;

    /**
     * 客服昵称 客服1
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
