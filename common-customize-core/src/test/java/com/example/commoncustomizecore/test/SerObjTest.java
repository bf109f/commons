package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.commons.CommonCoreUtils;
import com.example.commoncustomizecore.api.weChat.officialaccounts.model.SignInfo;
import com.example.commoncustomizecore.info.SubSign;
import org.junit.Test;

public class SerObjTest
{
    @Test
    public void test()
    {
        SubSign subSign= new SubSign();

        SignInfo signInfo = new SignInfo();
        subSign.setSignInfo(signInfo);
        subSign.setSubName("subSignName");
        signInfo.setNonce("你好");
        signInfo.setSignature("sign");
        signInfo.setTimestamp("2020");
        signInfo.setToken("token验证");
        String ser = CommonCoreUtils.serialize2string(subSign);
        System.out.println(CommonCoreUtils.unSerialize(ser));
        byte [] bytes = CommonCoreUtils.serialize2array(subSign);
        System.out.println(CommonCoreUtils.unSerialize(bytes));
    }
}
