package com.hxg.api.encryption.security;

import com.hxg.api.encryption.security.base.SingleEncryption;

/**
 * md5 方式加密
 * @see org.apache.commons.codec.digest.DigestUtils#md5Hex(String) 
 */
public class Md5 extends SingleEncryption
{
    private static final String ALGORITHM = "MD5";

    public Md5()
    {

    }

    public Md5(String prefix)
    {
        super(prefix);
    }

    public Md5(String prefix, String suffix)
    {
        super(prefix, suffix);
    }

    @Override
    public String encrypt(String content, String charset)
    {

        return encrypt(content, ALGORITHM, charset);
    }
}
