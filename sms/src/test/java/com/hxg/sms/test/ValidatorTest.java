package com.hxg.sms.test;

import com.hxg.core.validator.ObjectValidatorUtils;
import com.hxg.sms.supplier.gyytz.model.SendReq;
import org.junit.Test;

public class ValidatorTest
{
    @Test
    public void test()
    {
        SendReq req = new SendReq();
        ObjectValidatorUtils.validatorObj(req);
    }
}
