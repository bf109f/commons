package com.hxg.api.http;

import com.hxg.api.http.model.Request;
import com.hxg.api.http.model.Response;

/**
 * http请求接口
 */
public interface HttpSend {

    /**
     * get请求
     *
     * @param request 请求参数
     * @return Response
     */
    <P extends Response, Q> P sendGet(String url, Request<Q> request);

    /**
     * post请求
     *
     * @param request 请求参数
     * @return Response
     */
    <P extends Response, Q> P sendPost(String url, Request<Q> request);
}
