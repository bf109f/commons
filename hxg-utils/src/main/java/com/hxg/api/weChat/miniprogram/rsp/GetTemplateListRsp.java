package com.hxg.api.weChat.miniprogram.rsp;

import com.hxg.api.weChat.WXBaseRsp;
import com.hxg.api.weChat.miniprogram.model.TemplateInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 获取当前帐号下的个人模板列表
 */
@Data
public class GetTemplateListRsp extends WXBaseRsp
{
    /**
     * 模板列表
     */
    private List<TemplateInfo> data;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
