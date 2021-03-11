package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.SingleEncryption;

/**
 * SHA 方式加密
 */
public class Sha extends SingleEncryption
{
    private static final String ALGORITHM = "SHA";

    public Sha()
    {

    }

    public Sha(String prefix)
    {
        super(prefix);
    }

    public Sha(String prefix, String suffix)
    {
        super(prefix, suffix);
    }

    @Override
    public String encrypt(String content)
    {
        return encrypt(content, ALGORITHM);
    }
}
