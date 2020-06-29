package com.example.commoncustomizecore.api.weChat.miniprogram.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.httputils.HttpUtils;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.LoginReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.LoginRsp;
import com.example.commoncustomizecore.api.weChat.miniprogram.service.MiniProgramService;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MiniProgramServiceImpl extends MiniProgramService
{
    private static final Logger LOG = LoggerFactory.getLogger(MiniProgramServiceImpl.class);

    @Override
    public LoginRsp login(LoginReq req) throws CommonsCoreException
    {
        List<NameValuePair> pairs = HttpUtils.obj2List(req);
        String rsp = HttpUtils.sendGet(pairs, "https://api.weixin.qq.com/sns/jscode2session");
        LOG.info("getAccessToken rsp = {}", rsp);
        LoginRsp loginRsp = JSON.parseObject(rsp, LoginRsp.class);

        return loginRsp;
    }
}
