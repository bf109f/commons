package com.example.commoncustomizecore.api.weChat.miniprogram.service;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.weChat.WXService;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.LoginReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.LoginRsp;

public abstract class MiniProgramService extends WXService
{
    /**
     * 小程序登陆
     * @param req
     * @return
     * @throws CommonsCoreException
     */
    public abstract LoginRsp login(LoginReq req) throws CommonsCoreException;

}
