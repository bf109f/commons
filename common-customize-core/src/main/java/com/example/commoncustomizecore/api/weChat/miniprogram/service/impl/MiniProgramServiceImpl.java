package com.example.commoncustomizecore.api.weChat.miniprogram.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.httputils.HttpUtils;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.GetPubTemplateTitlesReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.LoginReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.GetCategoryRsp;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.GetPubTemplateTitleListRsp;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.GetTemplateListRsp;
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
    public GetPubTemplateTitleListRsp getPubTemplateTitleList(GetPubTemplateTitlesReq req) throws CommonsCoreException
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
}
