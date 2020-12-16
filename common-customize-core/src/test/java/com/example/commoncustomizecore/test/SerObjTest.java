package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.commons.CommonCoreUtils;
import com.example.commoncustomizecore.api.weChat.officialaccounts.model.SignInfo;
import org.junit.Test;

public class SerObjTest
{
    @Test
    public void test()
    {
        SignInfo signInfo = new SignInfo();
        signInfo.setNonce("你好");
        signInfo.setSignature("sign");
        signInfo.setTimestamp("2020");
        signInfo.setToken("token验证");
        String ser = CommonCoreUtils.serialize2string(signInfo);
        System.out.println(CommonCoreUtils.unSerialize(ser));
        byte [] bytes = CommonCoreUtils.serialize2array(signInfo);
        System.out.println(CommonCoreUtils.unSerialize(bytes));
    }
}
