package com.example.commoncustomizecore.api.weChat.officialaccounts.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.httputils.HttpUtils;
import com.example.commoncustomizecore.api.weChat.WXBaseRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.AddKfReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.SendKfTextMessageReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.SendTemplateMessageReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetTemplateListRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.service.OfficialAccountsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfficialAccountsServiceImpl extends OfficialAccountsService
{

    private static final Logger LOG = LoggerFactory.getLogger(OfficialAccountsServiceImpl.class);

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
        GetTemplateListRsp rsp = JSON.parseObject(templateList, GetTemplateListRsp.class);

        return rsp;
    }
}
