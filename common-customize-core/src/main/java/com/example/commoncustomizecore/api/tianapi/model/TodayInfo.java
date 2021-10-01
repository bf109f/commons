package com.example.commoncustomizecore.api.tianapi.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class TodayInfo
{
    /**
     * 当前阳历日期
     */
    private String date;

    /**
     * 日期类型，为0表示工作日、为1节假日、为2双休日、3为调休日（上班）
     */
    private int dayCode;

    /**
     * 日期描述 休 或 班
     */
    private String dayDesc;

    /**
     * 星期（数字）
     */
    private int weekday;

    /**
     * 星期（中文）
     */
    private String cnWeekday;

    /**
     * 农历年
     */
    private String lunarYear;

    /**
     * 农历月
     */
    private String lunarMonth;

    /**
     * 农历日
     */
    private String lunarDay;

    /**
     * 文字提示，工作日、节假日、节日、双休日
     */
    private String info;

    /**
     * 是否计薪 1 计薪 0 不计薪
     */
    private int whetherPaid;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
