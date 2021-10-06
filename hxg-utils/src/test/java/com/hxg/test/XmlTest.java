package com.hxg.test;

import com.hxg.api.weChat.officialaccounts.model.xmlbean.TextMessage;
import com.hxg.api.weChat.officialaccounts.util.OfficialAccountUtil;
import com.hxg.api.weChat.officialaccounts.util.XmlUtil;
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

    @Test
    public void getMessageType()
    {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><MsgType><![CDATA[event]]></MsgType><MsgType><![CDATA[good]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        OfficialAccountUtil.getMessageType(xml);
    }




}
