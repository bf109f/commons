package com.example.commoncustomizecore.api.weChat.publicaccount.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.httputils.HttpUtils;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.AddKfReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.GetTokenReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.SendKfTextMessageReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.SendTemplateMessageReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.rsp.GetTokenRsp;
import com.example.commoncustomizecore.api.weChat.publicaccount.rsp.WXBaseRsp;
import com.example.commoncustomizecore.api.weChat.publicaccount.service.WXService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PublicAccountServiceImpl implements WXService
{

    private static final Logger LOG = LoggerFactory.getLogger(PublicAccountServiceImpl.class);

    @Override
    public GetTokenRsp getAccessToken(GetTokenReq req) throws CommonsCoreException
    {
        /**
         * 转换pair对象
         */
        List<NameValuePair> pairs = HttpUtils.obj2List(req);
        String rsp = HttpUtils.sendGet(pairs, "https://api.weixin.qq.com/cgi-bin/token");
        LOG.info("getAccessToken rsp = {}", rsp);
        JSONObject rspObj = JSON.parseObject(rsp);
        String token = rspObj.getString("access_token");
        long expires_in = rspObj.getLongValue("expires_in");
        if (StringUtils.isBlank(token))
        {
            throw new CommonsCoreException(rsp);
        }
        GetTokenRsp getTokenRsp = new GetTokenRsp();
        getTokenRsp.setAccess_token(token);
        getTokenRsp.setExpires_in(expires_in);

        return getTokenRsp;
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
}
