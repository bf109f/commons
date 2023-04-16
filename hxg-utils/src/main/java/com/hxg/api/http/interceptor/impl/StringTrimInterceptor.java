package com.hxg.api.http.interceptor.impl;

import com.hxg.api.http.interceptor.ReqInterceptor;
import com.hxg.api.http.model.Request;

public class StringTrimInterceptor implements ReqInterceptor {
    @Override
    public void doInterceptor(Request request) {
        Object param = request.getParam();
        if (param instanceof String) {
            param = ((String) request.getParam()).trim();
            request.setParam(param);
        }
    }

    @Override
    public boolean match(String url) {
        return true;
    }
}
