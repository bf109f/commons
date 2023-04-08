package com.hxg.api.http;

import com.hxg.api.http.interceptor.ReqInterceptor;
import com.hxg.api.http.interceptor.RspInterceptor;
import com.hxg.api.http.model.Request;
import com.hxg.api.http.model.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * httpClient发送请求
 */
@NoArgsConstructor
@AllArgsConstructor
public class HttpClientSend implements HttpSend {

    /**
     * 请求参数处理
     */
    private List<ReqInterceptor<?>> reqInterceptors;

    /**
     * 响应数据处理
     */
    private List<RspInterceptor<?>> rspInterceptors;


    @Override
    public <P extends Response, Q> P sendGet(String url, Request<Q> request) {

        return null;
    }

    @Override
    public <P extends Response, Q> P sendPost(String url, Request<Q> request) {
        return null;
    }

    private <Q> void prepareParam(String url, Request<Q> request) {
        //字符串参数处理
        if (request.getParam() instanceof String) {

        }
    }
}
