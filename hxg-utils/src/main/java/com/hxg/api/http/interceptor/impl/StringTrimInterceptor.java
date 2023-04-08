package com.hxg.api.http.interceptor.impl;

import com.hxg.api.http.interceptor.ReqInterceptor;
import org.apache.commons.lang3.StringUtils;

public class StringTrimInterceptor implements ReqInterceptor<String> {
    @Override
    public String execute(String param) {
        return StringUtils.isBlank(param) ? param : param.trim();
    }

    @Override
    public boolean match(String url) {
        return true;
    }
}
