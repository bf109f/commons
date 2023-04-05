package com.hxg.api.http.interceptor;

/**
 * 请求拦截
 */
public interface HttpReqInterceptor<T> {
    /**
     * 处理请求参数
     *
     * @param param 请求参数
     * @return 处理后的参数
     */
    T execute(T param);

    /**
     * 判断是否需要拦截请求
     *
     * @param name 请求参数
     * @return boolean
     */
    boolean match(String name);
}
