package com.hxg.api.http.model;

import lombok.Data;

/**
 * 单个数据
 *
 * @param <T>
 */
@Data
public class SingleRsp<T> extends Response {
    /**
     * 响应内容
     */
    private T content;
}
