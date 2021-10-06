package com.hxg.api.weChat.miniprogram.rsp;

import com.hxg.api.weChat.WXBaseRsp;
import com.hxg.api.weChat.miniprogram.model.PubTemplateTitleInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 获取帐号所属类目下的公共模板标题
 */
@Data
public class GetPubTemplateTitleListRsp extends WXBaseRsp
{

    /**
     * 个数
     */
    private String count;

    /**
     * 模板信息列表
     */
    private List<PubTemplateTitleInfo> data;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
