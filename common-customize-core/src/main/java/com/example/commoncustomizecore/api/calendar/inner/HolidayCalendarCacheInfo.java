package com.example.commoncustomizecore.api.calendar.inner;

import com.example.commoncustomizecore.api.tianapi.model.NewsInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Data
public class HolidayCalendarCacheInfo
{
    /**
     * 数据列表
     */
    private List<NewsInfo> infos;

    /**
     * 保存时间
     */
    private Long saveTime;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
