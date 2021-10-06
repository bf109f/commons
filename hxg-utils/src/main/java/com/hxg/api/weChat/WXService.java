package com.hxg.api.weChat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hxg.api.exception.CommonsCoreException;
import com.hxg.api.httputils.HttpUtils;
import com.hxg.api.weChat.officialaccounts.req.GetTokenReq;
import com.hxg.api.weChat.officialaccounts.rsp.GetTokenRsp;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 微信基类
 */
public abstract class WXService
{
    private static final Logger LOG = LoggerFactory.getLogger(WXService.class);

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
}
