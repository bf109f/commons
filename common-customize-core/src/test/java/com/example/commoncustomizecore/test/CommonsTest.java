package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.commons.TimeUtils;
import com.example.commoncustomizecore.api.weChat.miniprogram.model.TemplateDataInfo;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.GetPubTemplateTitlesReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.LoginReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.req.SendMiniTemplateMessageReq;
import com.example.commoncustomizecore.api.weChat.miniprogram.rsp.LoginRsp;
import com.example.commoncustomizecore.api.weChat.miniprogram.service.MiniProgramService;
import com.example.commoncustomizecore.api.weChat.miniprogram.service.impl.MiniProgramServiceImpl;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.GetTokenReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.SendKfTextMessageReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.SendTemplateMessageReq;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.info.MessageDataInfo;
import com.example.commoncustomizecore.api.weChat.officialaccounts.req.info.TextInfo;
import com.example.commoncustomizecore.api.weChat.officialaccounts.rsp.GetTokenRsp;
import com.example.commoncustomizecore.api.weChat.WXBaseRsp;
import com.example.commoncustomizecore.api.weChat.officialaccounts.service.OfficialAccountsService;
import com.example.commoncustomizecore.api.weChat.officialaccounts.service.impl.OfficialAccountsServiceImpl;
import com.example.commoncustomizecore.info.MiniTemplateInfo;
import com.example.commoncustomizecore.info.OfficialAccountsInfo;
import com.example.commoncustomizecore.info.WeChatInfo;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonsTest
{
    private static String token;

    private static MiniProgramService miniService;

    private static String miniAppId;

    private static String miniAppSecret;

    static
    {
        OfficialAccountsService wxService = new OfficialAccountsServiceImpl();
        GetTokenReq getTokenReq = new GetTokenReq();
        WeChatInfo weChatInfo = new WeChatInfo();
        miniAppId = weChatInfo.getAppId();
        miniAppSecret = weChatInfo.getAppSecret();
        getTokenReq.setAppid(miniAppId);
        getTokenReq.setSecret(miniAppSecret);
        // 当使用多态方式调用方法时，首先检查父类中是否有该方法，如果有，再去调用子类的同名方法；如果没有，则编译错误。
        GetTokenRsp getTokenRsp = wxService.getAccessToken(getTokenReq);
        token = getTokenRsp.getAccess_token();
        miniService = new MiniProgramServiceImpl();
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
        OfficialAccountsInfo accountsInfo = new OfficialAccountsInfo();
        req.setTemplate_id(accountsInfo.getTemplateId());
        req.setTouser(accountsInfo.getToUser());
        req.setUrl("https://www.baidu.com");

        List<MessageDataInfo> infos = new ArrayList<>();
        infos.add(new MessageDataInfo("name", "bf109", "#173177"));
        infos.add(new MessageDataInfo("id", "sina_pay_64", "#173177"));
        infos.add(new MessageDataInfo("cash", "666.6", "#173177"));
        infos.add(new MessageDataInfo("created_at", "2020-05-20 00:00:00", "#173177"));
        infos.add(new MessageDataInfo("remark", "感谢您的信任", "#173177"));
        req.setData(infos);
        System.out.println(req);
        OfficialAccountsService wxService = new OfficialAccountsServiceImpl();

        wxService.sendTemplateMessage(req, token);
    }

    @Test
    public void getTemplateList()
    {
        OfficialAccountsService wxService = new OfficialAccountsServiceImpl();
        System.out.println(wxService.getTemplateList(token));
    }

    @Test
    public void sendKfMessage()
    {
        OfficialAccountsService wxService = new OfficialAccountsServiceImpl();

        SendKfTextMessageReq req = new SendKfTextMessageReq();
        OfficialAccountsInfo accountsInfo = new OfficialAccountsInfo();
        req.setTouser(accountsInfo.getToUser());
        req.setText(new TextInfo("hello 中国"));
        wxService.sendKfMessage(req, token);
    }

    @Test
    public void setJson()
    {
        WXBaseRsp rsp = new WXBaseRsp();
        rsp.setErrmsg("中文乱码");
        String rspString = rsp.toString();
        System.out.println(rspString);
    }

    @Test
    public void testCommons()
    {
        try
        {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-10 14:00:00");
            System.out.println(TimeUtils.getDayOfWeek(date));
            System.out.println(TimeUtils.getDayOfWeek());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

//    @Test
    public void testWord()
    {
        try
        {
            StringBuilder buffer;
            InputStream is = new FileInputStream(new File("/Volumes/Untitled/code/计算机基础考试题库/1.10多媒体技术测验.doc"));


//            XWPFDocument docx = new XWPFDocument(is);
//            HWPFDocument doc = new HWPFDocument(is);
            /*InputStream is = new FileInputStream(new File(filePath));
            WordExtractor ex = new WordExtractor(is);*/
            WordExtractor ex = new WordExtractor(is);
//            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
//            WordExtractor extractor = new WordExtractor(doc);
//            WordExtractor ex = new WordExtractor(is);
            String s = ex.getText();
//            ex.close();

            is.close();
            System.out.println(s);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void testLogin()
    {
        LoginReq req = new LoginReq();
        req.setAppid(miniAppId);
        req.setSecret(miniAppSecret);
        req.setJs_code("021RTXSs0oxFbj1b7RUs0rz9Ts0RTXS8");

        LoginRsp rsp = miniService.login(req);
        System.out.println(rsp);
    }

    @Test
    public void getCategory()
    {

        miniService.getCategory(token);
    }

    @Test
    public void getPubTemplateTitleList()
    {
        GetPubTemplateTitlesReq req = new GetPubTemplateTitlesReq();
        req.setIds("225,298,698");
        req.setLimit("30");
        req.setStart("0");
        req.setAccess_token(token);
        System.out.println(miniService.getPubTemplateTitleList(req, token));
    }

    @Test
    public void getMiniTemplateList()
    {
        System.out.println(miniService.getTemplateList(token));
    }

    @Test
    public void sendMiniTemplateMessage()
    {
        SendMiniTemplateMessageReq req = new SendMiniTemplateMessageReq();
        MiniTemplateInfo info = new MiniTemplateInfo();
        req.setTemplate_id(info.getTemplateId());
        req.setTouser(info.getToUser());
        TemplateDataInfo dataInfo1 = new TemplateDataInfo("thing1", "下班打卡");
        TemplateDataInfo dataInfo2 = new TemplateDataInfo("time2", "18:30");
        TemplateDataInfo dataInfo3 = new TemplateDataInfo("thing3", "世纪广场");
        TemplateDataInfo dataInfo4 = new TemplateDataInfo("thing4", "下班打卡");
        List<TemplateDataInfo> infos = new ArrayList<>();
        infos.add(dataInfo1);
        infos.add(dataInfo2);
        infos.add(dataInfo3);
        infos.add(dataInfo4);
        req.setData(infos);
        miniService.sendTemplateMessage(req, token);
        // {"errcode":47003,"errmsg":"argument invalid! data.thing3.value is emtpy rid: 5f325159-44411d21-1eb8b6c3"}
        // {"errcode":0,"errmsg":"ok"}
        // {"errcode":43101,"errmsg":"user refuse to accept the msg rid: 5f325211-48269097-7624df34"}
    }

}
