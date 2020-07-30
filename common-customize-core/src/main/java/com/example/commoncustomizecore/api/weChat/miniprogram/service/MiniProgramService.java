package com.example.commoncustomizecore.api.weChat.miniprogram.service;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.weChat.WXService;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.GetPubTemplateTitlesReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.LoginReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.GetCategoryRsp;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.GetPubTemplateTitleListRsp;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.GetTemplateListRsp;
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
     * @throws CommonsCoreException
     */
    public abstract GetPubTemplateTitleListRsp getPubTemplateTitleList(GetPubTemplateTitlesReq req) throws CommonsCoreException;

    /**
     * 获取当前帐号下的个人模板列表
     * @param token
     * @return
     * @throws CommonsCoreException
     */
    public abstract GetTemplateListRsp getTemplateList(String token) throws CommonsCoreException;

}
