package com.hxg.api.http.interceptor;

/**
 * 响应拦截
 */
public interface HttpRspInterceptor<P> {
    /**
     * 响应处理
     *
     * @param rsp 响应
     * @return P
     */
    P execute(P rsp);
}
