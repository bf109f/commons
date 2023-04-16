package com.hxg.api.http.interceptor;

import com.hxg.api.http.model.Request;

/**
 * 请求拦截
 */
public interface ReqInterceptor {
    /**
     * 处理请求参数
     *
     * @param param 请求参数
     */
    void doInterceptor(Request<?> param);

    /**
     * 判断是否需要拦截请求
     *
     * @param url 请求url
     * @return boolean
     */
    boolean match(String url);
}
