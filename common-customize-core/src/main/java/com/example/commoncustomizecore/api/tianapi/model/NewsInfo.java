package com.example.commoncustomizecore.api.tianapi.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class NewsInfo
{
    /**
     * 当前阳历日期
     */
    private String date;

    /**
     * 日期类型，为0表示工作日、为1节假日、为2双休日、3为调休日（上班）
     */
    private int daycode;

    /**
     * 星期（数字）
     */
    private int weekday;

    /**
     * 星期（中文）
     */
    private String cnweekday;

    /**
     * 农历年
     */
    private String lunaryear;

    /**
     * 农历月
     */
    private String lunarmonth;

    /**
     * 农历日
     */
    private String lunarday;

    /**
     * 文字提示，工作日、节假日、节日、双休日
     */
    private String info;

    /**
     * 假期起点计数
     */
    private int start;

    /**
     * 假期当前计数
     */
    private int now;

    /**
     * 假期终点计数
     */
    private int end;

    /**
     * 节日日期
     */
    private String holiday;

    /**
     * 节假日名称（中文）
     */
    private String name;

    /**
     * 节日名称（英文）
     */
    private String enname;

    /**
     * 是否需要上班，0为工作日，1为休息日
     */
    private int isnotwork;

    /**
     * 节假日数组 2021-09-19|2021-09-20|2021-09-21
     */
    private String vacation;

    /**
     * 调休日数组 2021-09-26|2021-10-09
     */
    private String remark;

    /**
     * 薪资法定倍数
     */
    private String wage;

    /**
     * 放假提示
     */
    private String tip;

    /**
     * 拼假建议
     */
    private String rest;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
