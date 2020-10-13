package com.example.commoncustomizecore.api.weChat.officialaccounts.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公众号验签对象
 */
@Data
public class SignInfo
{
    /**
     * token
     */
    private String token;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 随机数
     */
    private String nonce;

    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     */
    private String signature;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
