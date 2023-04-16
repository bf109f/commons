package com.hxg.api.http;

import com.hxg.api.http.model.Request;
import com.hxg.api.http.model.Response;

/**
 * 请求信息保存
 */
public interface HttpSave {

    /**
     * 数据保存
     *
     * @param url      请求url
     * @param request  请求参数
     * @param response 响应数据
     */
    void save(String url, Request<?> request, Response response);
}
