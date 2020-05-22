package com.example.commoncustomizecore.api.weChat.publicaccount.service;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.AddKfReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.GetTokenReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.SendKfTextMessageReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.SendTemplateMessageReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.rsp.GetTokenRsp;
import com.example.commoncustomizecore.api.weChat.publicaccount.rsp.WXBaseRsp;

public interface WXService
{
    /**
     * 获取token
     * @param req
     * @return
     * @throws CommonsCoreException
     */
    GetTokenRsp getAccessToken(GetTokenReq req) throws CommonsCoreException;

    /**
     * 添加客服
     * @param req
     * @param token
     * @throws CommonsCoreException
     */
    void addKf(AddKfReq req, String token) throws CommonsCoreException;

    /**
     * 发送客服消息
     * @param req
     * @param token
     * @return
     * @throws CommonsCoreException
     */
    WXBaseRsp sendKfMessage(SendKfTextMessageReq req, String token) throws CommonsCoreException;

    /**
     * 发送客服消息
     * @param req
     * @param token
     * @return
     * @throws CommonsCoreException
     */
    WXBaseRsp sendTemplateMessage(SendTemplateMessageReq req, String token) throws CommonsCoreException;
}
