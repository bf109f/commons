package com.hxg.api.http.model;

import lombok.Data;

@Data
public class Response {
    /**
     * 响应描述
     */
    private String message;

    /**
     * 响应码
     */
    private int code;
}
