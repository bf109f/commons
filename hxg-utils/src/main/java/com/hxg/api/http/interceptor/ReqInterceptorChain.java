package com.hxg.api.http.interceptor;

import com.hxg.api.http.model.Request;

public interface ReqInterceptorChain {
    void doInterceptor(Request request);
}
