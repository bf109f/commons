package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.httputils.HttpUtils;
import org.junit.Test;

public class HttpTest
{
    @Test
    public void test()
    {
        HttpUtils.sendGet("https://baidu.com");
    }
}
