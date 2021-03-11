package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.AsymmetricEncryption;

import java.io.File;

/**
 * RSA 公钥加密 私钥解密
 */
public class RsaPositive extends AsymmetricEncryption
{
    public RsaPositive(File privateFile, File publicFile)
    {
        super(privateFile, publicFile);
    }

    public RsaPositive(String privateKey, String publicKey)
    {
        super(privateKey, publicKey);
    }

    @Override
    public String decrypt(String content)
    {
        return null;
    }

    @Override
    public String encrypt(String content)
    {
        return null;
    }
}
