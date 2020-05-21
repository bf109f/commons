package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.weChat.publicaccount.req.GetTokenReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.SendKfTextMessageReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.SendTemplateMessageReq;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.info.MessageDataInfo;
import com.example.commoncustomizecore.api.weChat.publicaccount.req.info.TextInfo;
import com.example.commoncustomizecore.api.weChat.publicaccount.rsp.GetTokenRsp;
import com.example.commoncustomizecore.api.weChat.publicaccount.service.IService;
import com.example.commoncustomizecore.api.weChat.publicaccount.service.impl.PublicAccountServiceImpl;
import com.example.commoncustomizecore.info.WeChatInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommonsTest
{
    private static String token;

    static
    {
        IService iService = new PublicAccountServiceImpl();
        GetTokenReq getTokenReq = new GetTokenReq();
        getTokenReq.setAppid(new WeChatInfo().getAppId());
        getTokenReq.setSecret(new WeChatInfo().getAppSecret());
        GetTokenRsp getTokenRsp = iService.getAccessToken(getTokenReq);
        token = getTokenRsp.getAccess_token();
    }

    @Test
    public void sendTemplateMessage()
    {
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "张三");
        JSONObject con = new JSONObject();
        con.put("name", "数学");
        con.put("value", "36");
        jsonObject.put("console", con);
        System.out.println(jsonObject.toString());*/

        SendTemplateMessageReq req = new SendTemplateMessageReq();
        req.setTemplate_id("");
        req.setTouser("");
        req.setUrl("https://www.baidu.com");

        List<MessageDataInfo> infos = new ArrayList<>();
        infos.add(new MessageDataInfo("name", "bf109", "#173177"));
        infos.add(new MessageDataInfo("id", "sina_pay_64", "#173177"));
        infos.add(new MessageDataInfo("cash", "666.6", "#173177"));
        infos.add(new MessageDataInfo("created_at", "2020-05-20 00:00:00", "#173177"));
        infos.add(new MessageDataInfo("remark", "感谢您的信任", "#173177"));
        req.setData(infos);
        System.out.println(req);
        IService iService = new PublicAccountServiceImpl();

        iService.sendTemplateMessage(req, token);
    }

    @Test
    public void sendKfMessage()
    {
        IService iService = new PublicAccountServiceImpl();

        SendKfTextMessageReq req = new SendKfTextMessageReq();
        req.setTouser("");
        req.setText(new TextInfo("hello 中国"));
        iService.sendKfMessage(req, token);
    }
}
