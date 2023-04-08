package com.hxg.api.http.interceptor;

/**
 * 响应拦截
 */
public interface RspInterceptor<P> {
    /**
     * 响应处理
     *
     * @param rsp 响应
     * @return P
     */
    P execute(P rsp);

    /**
     * 判断是否需要拦截请求
     *
     * @param url 请求url
     * @return boolean
     */
    boolean match(String url);
}
