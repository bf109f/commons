package com.hxg.api.weChat.officialaccounts.req.info;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 二维码使用场景
 */
@Data
public class QrcodeSceneInfo
{

    /**
     * 场景值ID，
     * 临时二维码时为32位非0整型，
     * 永久二维码时最大值为100000（目前参数只支持1--100000）
     */
    private int scene_id;

    /**
     * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     */
    private String scene_str;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
