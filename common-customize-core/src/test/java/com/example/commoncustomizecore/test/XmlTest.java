package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.weChat.officialaccounts.model.xmlbean.TextMessage;
import com.example.commoncustomizecore.api.weChat.officialaccounts.util.XmlUtil;
import org.junit.Test;

public class XmlTest
{
    @Test
    public void obj2xml()
    {
        TextMessage textMessage = new TextMessage();
        textMessage.setContent("xml内容");
        textMessage.setCreateTime(12558647L);
        textMessage.setFromUserName("bf109");
        textMessage.setMsgId("15975");
        textMessage.setMsgType("text");
        textMessage.setToUserName("公众号");
        System.out.println(XmlUtil.obj2Xml(textMessage));
    }

    @Test
    public void xml2obj()
    {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        TextMessage textMessage = XmlUtil.xml2obj(TextMessage.class, xml);
        System.out.println(textMessage);
    }






}
