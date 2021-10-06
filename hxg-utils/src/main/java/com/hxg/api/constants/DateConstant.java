package com.hxg.api.constants;

public interface DateConstant
{

    /**
     * 调休日
     */
    String REST_DAY = "班";

    /**
     * 节假日
     */
    String HOLIDAYS = "休";

    /**
     * 法定记薪日
     */
    String STATUTORY_PAYDAY = "statutory_payday";

    /**
     *  日期格式化，"yyyy-MM-dd"
     */
    String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";

    /**
     * 日期格式化，"yyyyMMdd"
     */
    String DATE_FORMAT_YEAR_MONTH_DAY2 = "yyyyMMdd";

    /**
     * 法定节假日记薪天数
     */
    int STATUTORY_PAY_DAYS = 11;

    /**
     * 记薪
     */
    int PAY = 1;

    /**
     * 不记薪
     */
    int NOT_PAY = 0;
}
