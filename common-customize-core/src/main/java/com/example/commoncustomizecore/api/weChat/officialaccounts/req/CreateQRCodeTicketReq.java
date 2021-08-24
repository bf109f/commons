package com.example.commoncustomizecore.api.weChat.officialaccounts.req;

import com.example.commoncustomizecore.api.weChat.officialaccounts.req.info.ActionInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class CreateQRCodeTicketReq
{
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    private long expire_seconds;

    /**
     * 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，
     * QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    private String action_name;

    /**
     * 二维码信息
     */
    private ActionInfo action_info;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
