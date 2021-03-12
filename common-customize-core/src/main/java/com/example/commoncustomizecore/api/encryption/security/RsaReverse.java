package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.AsymmetricEncryption;

import javax.crypto.Cipher;
import java.io.File;

/**
 * RSA 私钥加密 公钥解密
 */
public class RsaReverse extends AsymmetricEncryption
{
    public RsaReverse(File privateFile, File publicFile)
    {
        super(privateFile, publicFile);
    }

    public RsaReverse(String privateKey, String publicKey)
    {
        super(privateKey, publicKey);
    }

    @Override
    public String decrypt(String content)
    {
        return dealRsaCipher(publicKey, Cipher.DECRYPT_MODE, MAX_DECRYPT_BLOCK, content.getBytes());
    }

    @Override
    public String encrypt(String content, String charset)
    {
        return dealRsaCipher(privateKey, Cipher.ENCRYPT_MODE, MAX_ENCRYPT_BLOCK, content.getBytes());
    }
}
