package com.hxg.api.weChat.miniprogram.rsp;

import com.hxg.api.weChat.WXBaseRsp;
import com.hxg.api.weChat.miniprogram.model.CategoryInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 获取小程序账号的类目列表
 */
@Data
public class GetCategoryRsp extends WXBaseRsp
{
    /**
     * 类目列表
     */
    private List<CategoryInfo> data;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
