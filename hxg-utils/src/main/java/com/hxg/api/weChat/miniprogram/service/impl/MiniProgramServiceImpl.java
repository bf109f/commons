package com.hxg.api.weChat.miniprogram.service.impl;

import com.alibaba.fastjson.JSON;
import com.hxg.api.exception.CommonsCoreException;
import com.hxg.api.httputils.HttpUtils;
import com.hxg.api.weChat.miniprogram.req.GetPubTemplateTitlesReq;
import com.hxg.api.weChat.miniprogram.req.LoginReq;
import com.hxg.api.weChat.miniprogram.req.SendMiniTemplateMessageReq;
import com.hxg.api.weChat.miniprogram.rsp.*;
import com.hxg.api.weChat.miniprogram.service.MiniProgramService;
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
        LOG.info("LoginRsp = {}", rsp);
        LoginRsp loginRsp = JSON.parseObject(rsp, LoginRsp.class);

        return loginRsp;
    }

    @Override
    public GetCategoryRsp getCategory(String token) throws CommonsCoreException
    {
        String rsp = HttpUtils.sendGet("https://api.weixin.qq.com/wxaapi/newtmpl/getcategory?access_token=" + token);
        LOG.info("GetCategoryRsp = {}", rsp);
        GetCategoryRsp rspInfo = JSON.parseObject(rsp, GetCategoryRsp.class);
        return rspInfo;
    }

    @Override
    public GetPubTemplateTitleListRsp getPubTemplateTitleList(GetPubTemplateTitlesReq req, String token) throws CommonsCoreException
    {
        List<NameValuePair> pairs = HttpUtils.obj2List(req);
        String rsp = HttpUtils.sendGet(pairs, "https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatetitles");
        LOG.info("GetPubTemplateTitleListRsp = {}", rsp);
        GetPubTemplateTitleListRsp listRsp = JSON.parseObject(rsp, GetPubTemplateTitleListRsp.class);
        return listRsp;
    }

    @Override
    public GetTemplateListRsp getTemplateList(String token) throws CommonsCoreException
    {
        String rsp = HttpUtils.sendGet("https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=" + token);
        LOG.info("GetTemplateListRsp = {}", rsp);
        GetTemplateListRsp listRsp = JSON.parseObject(rsp, GetTemplateListRsp.class);
        return listRsp;
    }

    @Override
    public SendMiniTemplateMessageRsp sendTemplateMessage(SendMiniTemplateMessageReq req, String token) throws CommonsCoreException
    {
//        List<NameValuePair> pairs = HttpUtils.obj2List(req);
        String rsp = HttpUtils.sendPost(req.toString(), "https://api.weixin.qq.com/cgi-bin/message/subscribe/send" +
                "?access_token" +
                "=" + token);
        LOG.info("SendMiniTemplateMessageRsp = {}", rsp);
        SendMiniTemplateMessageRsp sendRsp = JSON.parseObject(rsp, SendMiniTemplateMessageRsp.class);
        return sendRsp;
    }
}
