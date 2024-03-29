package com.example.commoncustomizecore.api.weChat.officialaccounts.rsp;

import com.example.commoncustomizecore.api.weChat.WXBaseRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.vo.TemplateVo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Data
public class GetTemplateListRsp extends WXBaseRsp
{
    private List<TemplateVo> template_list;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
