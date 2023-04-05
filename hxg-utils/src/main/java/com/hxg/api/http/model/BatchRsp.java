package com.hxg.api.http.model;

import lombok.Data;

import java.util.List;

/**
 * 批量数据
 *
 * @param <T>
 */
@Data
public class BatchRsp<T> extends Response {
    /**
     * 响应内容
     */
    private List<T> contents;
}
