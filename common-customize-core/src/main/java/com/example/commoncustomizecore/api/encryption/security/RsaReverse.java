package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.AsymmetricEncryption;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.File;

/**
 * RSA 私钥加密 公钥解密
 */
public class RsaReverse extends AsymmetricEncryption
{
    /**
     *
     * @param privateFile 私钥文件
     * @param publicFile 公钥文件
     */
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
        return new String(dealRsaCipher(publicKey, Cipher.DECRYPT_MODE, MAX_DECRYPT_BLOCK, Base64.decodeBase64(content)));
    }

    @Override
    public String encrypt(String content, String charset)
    {
        return Base64.encodeBase64String(dealRsaCipher(privateKey, Cipher.ENCRYPT_MODE, MAX_ENCRYPT_BLOCK, content.getBytes()));
    }
}
