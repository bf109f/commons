package com.example.commoncustomizecore.api.tianapi.rsp;

import com.example.commoncustomizecore.api.tianapi.model.NewsInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Data
public class GetHolidaysRsp extends TianApiBaseRsp
{
    private List<NewsInfo> newslist;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
