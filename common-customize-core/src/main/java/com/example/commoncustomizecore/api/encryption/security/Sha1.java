package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.SingleEncryption;

/**
 * SHA 方式加密
 * @see org.apache.commons.codec.digest.DigestUtils#sha1Hex(String)
 */
public class Sha1 extends SingleEncryption
{
    private static final String ALGORITHM = "SHA";

    public Sha1()
    {

    }

    public Sha1(String prefix)
    {
        super(prefix);
    }

    public Sha1(String prefix, String suffix)
    {
        super(prefix, suffix);
    }

    @Override
    public String encrypt(String content, String charset)
    {
        return encrypt(content, ALGORITHM, charset);
    }
}
