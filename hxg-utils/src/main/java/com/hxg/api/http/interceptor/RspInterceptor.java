package com.hxg.api.http.interceptor;

import com.hxg.api.http.model.Response;

/**
 * 响应拦截
 */
public interface RspInterceptor {
    /**
     * 响应处理
     *
     * @param rsp 响应
     */
    void doInterceptor(Response rsp);

    /**
     * 判断是否需要拦截请求
     *
     * @param url 请求url
     * @return boolean
     */
    boolean match(String url);
}
