package com.hxg.api.weChat.miniprogram.service;

import com.hxg.api.exception.CommonsCoreException;
import com.hxg.api.weChat.WXService;
import com.hxg.api.weChat.miniprogram.req.GetPubTemplateTitlesReq;
import com.hxg.api.weChat.miniprogram.req.LoginReq;
import com.hxg.api.weChat.miniprogram.req.SendMiniTemplateMessageReq;
import com.hxg.api.weChat.miniprogram.rsp.*;

public abstract class MiniProgramService extends WXService
{
    /**
     * 小程序登陆
     * @param req
     * @return
     * @throws CommonsCoreException
     */
    public abstract LoginRsp login(LoginReq req) throws CommonsCoreException;

    /**
     * 获取小程序账号的类目
     * @param token
     * @return
     * @throws CommonsCoreException
     */
    public abstract GetCategoryRsp getCategory(String token) throws CommonsCoreException;

    /**
     * 获取帐号所属类目下的公共模板标题
     * @param req
     * @param token
     * @throws CommonsCoreException
     */
    public abstract GetPubTemplateTitleListRsp getPubTemplateTitleList(GetPubTemplateTitlesReq req, String token) throws CommonsCoreException;

    /**
     * 获取当前帐号下的个人模板列表
     * @param token
     * @return
     * @throws CommonsCoreException
     */
    public abstract GetTemplateListRsp getTemplateList(String token) throws CommonsCoreException;

    /**
     * 发送模板消息
     * @param req
     * @return
     * @throws CommonsCoreException
     */
    public abstract SendMiniTemplateMessageRsp sendTemplateMessage(SendMiniTemplateMessageReq req, String token) throws CommonsCoreException;

}
