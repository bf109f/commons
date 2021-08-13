package com.example.commoncustomizecore.api.weChat.officialaccounts.service;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.weChat.WXBaseRsp;
import com.example.commoncustomizecore.api.weChat.WXService;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.*;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetAuthAccessTokenRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetTemplateListRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetUserInfoRsp;

public abstract class OfficialAccountsService extends WXService
{

    /**
     * 根据网页授权返回code获取用户openID
     * @param req
     * @return
     * @throws CommonsCoreException
     */
    public abstract GetAuthAccessTokenRsp getAuthAccessToken(GetAuthAccessTokenReq req) throws CommonsCoreException;

    /**
     * 根据网页授权返回access_token获取微信用户信息
     * @param req
     * @return
     * @throws CommonsCoreException
     */
    public abstract GetUserInfoRsp getWechatUserInfo(GetUserInfoReq req) throws CommonsCoreException;

    /**
     * 添加客服
     * @param req
     * @param token
     * @throws CommonsCoreException
     */
    public abstract void addKf(AddKfReq req, String token) throws CommonsCoreException;

    /**
     * 发送客服消息
     * @param req
     * @param token
     * @return
     * @throws CommonsCoreException
     */
    public abstract WXBaseRsp sendKfMessage(SendKfTextMessageReq req, String token) throws CommonsCoreException;

    /**
     * 发送客服消息
     * @param req
     * @param token
     * @return
     * @throws CommonsCoreException
     */
    public abstract WXBaseRsp sendTemplateMessage(SendTemplateMessageReq req, String token) throws CommonsCoreException;

    /**
     * 获取模版列表
     * @param token
     * @return
     * @throws CommonsCoreException
     */
    public abstract GetTemplateListRsp getTemplateList(String token) throws CommonsCoreException;
}
