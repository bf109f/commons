package com.hxg.api.weChat.officialaccounts.rsp;

import com.hxg.api.weChat.WXBaseRsp;
import com.hxg.api.weChat.officialaccounts.rsp.vo.WechatUserVo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class GetUserInfoRsp extends WXBaseRsp
{
    private WechatUserVo userInfo;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
