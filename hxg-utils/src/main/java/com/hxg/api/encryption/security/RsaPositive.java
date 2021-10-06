package com.hxg.api.encryption.security;

import com.hxg.api.encryption.security.base.AsymmetricEncryption;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.File;

/**
 * RSA 公钥加密 私钥解密
 */
public class RsaPositive extends AsymmetricEncryption
{
    /**
     *
     * @param privateFile 私钥文件
     * @param publicFile 公钥文件
     */
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
        return new String(dealRsaCipher(privateKey, Cipher.DECRYPT_MODE, MAX_DECRYPT_BLOCK, Base64.decodeBase64(content)));
    }

    @Override
    public String encrypt(String content, String charset)
    {
        return Base64.encodeBase64String(dealRsaCipher(publicKey, Cipher.ENCRYPT_MODE, MAX_ENCRYPT_BLOCK, content.getBytes()));
    }
}
