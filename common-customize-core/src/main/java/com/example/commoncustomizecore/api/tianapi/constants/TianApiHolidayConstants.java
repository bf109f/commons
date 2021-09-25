package com.example.commoncustomizecore.api.tianapi.constants;

public interface TianApiHolidayConstants
{
    /**
     * 0批量
     */
    int TYPE_BATCH = 0;

    /**
     * 1按年
     */
    int TYPE_YEAR = 1;

    /**
     * 2按月
     */
    int TYPE_MONTH = 2;

    /**
     * 3范围
     */
    int TYPE_SCOPE = 3;

    /**
     * 1返回当天全部相关节日信息
     */
    int MODE_ALL = 1;

    /**
     * 工作日
     */
    int WORKING_DAY = 0;

    /**
     * 节假日
     */
    int HOLIDAYS = 1;

    /**
     * 双休日
     */
    int WEEKEND = 2;

    /**
     * 调休日 上班
     */
    int REST_DAY = 3;
}
