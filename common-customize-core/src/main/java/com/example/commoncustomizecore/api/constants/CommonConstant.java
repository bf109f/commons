package com.example.commoncustomizecore.api.constants;

public interface CommonConstant
{
    /**
     * 连接类型 url
     */
    String CONNECT_TYPE_URL = "url";

    /**
     * 连接类型 path 路径
     */
    String CONNECT_TYPE_PATH = "path";

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
