package com.example.commoncustomizecore.api.weChat.officialaccounts.service;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.weChat.WXBaseRsp;
import com.example.commoncustomizecore.api.weChat.WXService;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.AddKfReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.SendKfTextMessageReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.SendTemplateMessageReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetTemplateListRsp;

public abstract class OfficialAccountsService extends WXService
{

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
