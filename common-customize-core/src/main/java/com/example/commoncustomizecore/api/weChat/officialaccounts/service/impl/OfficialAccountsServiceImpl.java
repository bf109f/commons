package com.example.commoncustomizecore.api.weChat.officialaccounts.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.commoncustomizecore.api.commons.CommonCoreUtils;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.httputils.HttpUtils;
import com.example.commoncustomizecore.api.weChat.WXBaseRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.*;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.CreateQRCodeTicketRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetAuthAccessTokenRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetTemplateListRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetUserInfoRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.service.OfficialAccountsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;

public class OfficialAccountsServiceImpl extends OfficialAccountsService
{

    private static final Logger LOG = LoggerFactory.getLogger(OfficialAccountsServiceImpl.class);

    @Override
    public GetAuthAccessTokenRsp getAuthAccessToken(GetAuthAccessTokenReq req) throws CommonsCoreException
    {
        LOG.info("getAuthAccessToken req = {}", req);
        String wxRsp = HttpUtils.sendGet(req,"https://api.weixin.qq.com/sns/oauth2/access_token");
        LOG.info("getAuthAccessToken rsp = {}", wxRsp);
        return JSON.parseObject(wxRsp, GetAuthAccessTokenRsp.class);
    }

    @Override
    public GetUserInfoRsp getWechatUserInfo(GetUserInfoReq req) throws CommonsCoreException
    {
        LOG.info("getWechatUserInfo req = {}", req);
        String wxRsp = HttpUtils.sendGet(req, "https://api.weixin.qq.com/sns/userinfo");
        LOG.info("getWechatUserInfo rsp = {}", wxRsp);
        return JSON.parseObject(wxRsp, GetUserInfoRsp.class);
    }


    @Override
    public void addKf(AddKfReq req, String token) throws CommonsCoreException
    {
        String addKfRsp =
                HttpUtils.sendPost(req.toString(), "https://api.weixin.qq" +
                        ".com/customservice/kfaccount/add?access_token=" + token);
        LOG.info("addKf rsp = {}", addKfRsp);
        JSONObject jsonObject = JSON.parseObject(addKfRsp);
        String errCode = jsonObject.getString("errcode");
        if (!(StringUtils.isNotEmpty(errCode) && "0".equals(errCode)))
        {
            throw new CommonsCoreException(addKfRsp);
        }
    }

    @Override
    public WXBaseRsp sendKfMessage(SendKfTextMessageReq req, String token) throws CommonsCoreException
    {
        LOG.info("req = {}", req);
        String sendMessageRsp = HttpUtils.sendPost(req.toString(), "https://api.weixin.qq" +
                ".com/cgi-bin/message/custom/send?access_token=" + token);
        LOG.info("sendKfMessage rsp = {}", sendMessageRsp);
        JSONObject jsonObject = JSON.parseObject(sendMessageRsp);
        WXBaseRsp rsp = new WXBaseRsp();
        rsp.setErrcode(jsonObject.getInteger("errcode"));
        rsp.setErrmsg(jsonObject.getString("errmsg"));
        return rsp;
    }

    @Override
    public WXBaseRsp sendTemplateMessage(SendTemplateMessageReq req, String token) throws CommonsCoreException
    {
        String templateMessageRsp = HttpUtils.sendPost(req.toString(),
                "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token);
        LOG.info("sendKfMessage rsp = {}", templateMessageRsp);
        JSONObject jsonObject = JSON.parseObject(templateMessageRsp);
        WXBaseRsp rsp = new WXBaseRsp();
        rsp.setErrcode(jsonObject.getInteger("errcode"));
        rsp.setErrmsg(jsonObject.getString("errmsg"));
        return rsp;
    }

    @Override
    public GetTemplateListRsp getTemplateList(String token) throws CommonsCoreException
    {
        String templateList = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template" +
                "?access_token=" + token);
        LOG.info("sendKfMessage rsp = {}", templateList);

        return JSON.parseObject(templateList, GetTemplateListRsp.class);
    }

    @Override
    public CreateQRCodeTicketRsp createQRCodeTicket(CreateQRCodeTicketReq req, String token) throws CommonsCoreException
    {
        LOG.info("createQRCodeTicket req = {}", req);
        String rsp = HttpUtils.sendPost(req.toString(),
                "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token);
        LOG.info("createQRCodeTicket rsp = {}", rsp);
        return JSON.parseObject(rsp, CreateQRCodeTicketRsp.class);
    }

    @Override
    public void createQRCodePicture(String ticket, String path) throws CommonsCoreException
    {
        try
        {
            LOG.info("createQRCodePicture ticket = {}", ticket);
            String rsp =
                    HttpUtils.sendGet("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" +
                            URLEncoder.encode(ticket, "UTF-8"));
            File file = CommonCoreUtils.getFileByPath(path);
            OutputStream out = new FileOutputStream(file);
            out.write(Base64.getDecoder().decode(rsp));
            LOG.info("createQRCodePicture rsp = {}", rsp);
        } catch (UnsupportedEncodingException e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("url编码失败");
        } catch (IOException e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("文件保存失败");
        }

    }
}
