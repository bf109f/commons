package com.hxg.api.http.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Response {
    /**
     * 响应描述
     */
    private String message;

    /**
     * 响应码
     */
    private int httpCode;

    /**
     * 请求时间
     */
    private DateTime requestTime;

    /**
     * 响应时间
     */
    private DateTime responseTime;

    /**
     * 耗时
     */
    private long times;
}
