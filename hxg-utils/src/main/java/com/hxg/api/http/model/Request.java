package com.hxg.api.http.model;

import lombok.Data;

import java.util.Map;

@Data
public class Request<T> {
    /**
     * 请求参数
     */
    private T param;

    /**
     * 请求头
     */
    private Map<String, String> headers;
}
