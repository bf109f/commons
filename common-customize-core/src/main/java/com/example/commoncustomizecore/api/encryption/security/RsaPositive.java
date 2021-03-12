package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.AsymmetricEncryption;

import javax.crypto.Cipher;
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
        return dealRsaCipher(privateKey, Cipher.DECRYPT_MODE, MAX_DECRYPT_BLOCK, content.getBytes());
    }

    @Override
    public String encrypt(String content, String charset)
    {
        return dealRsaCipher(publicKey, Cipher.ENCRYPT_MODE, MAX_ENCRYPT_BLOCK, content.getBytes());
    }
}
