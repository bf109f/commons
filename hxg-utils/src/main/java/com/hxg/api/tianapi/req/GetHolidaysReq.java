package com.hxg.api.tianapi.req;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
@Data
public class GetHolidaysReq extends TianApiBaseReq
{

    /**
     * 查询日期或日期范围
     * 2021-01-01
     */
    private String date;

    /**
     * 工作模式，为1返回当天全部相关节日信息
     */
    private int mode;

    /**
     * 查询类型，0批量、1按年、2按月、3范围
     */
    private int type;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
