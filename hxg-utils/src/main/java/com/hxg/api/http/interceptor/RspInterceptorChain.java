package com.hxg.api.http.interceptor;

import com.hxg.api.http.model.Response;

public interface RspInterceptorChain {
    void doInterceptor(Response response);
}
