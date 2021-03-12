package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.AsymmetricEncryption;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

/**
 * RSA 公钥加签 私钥验签
 */
public class RsaSign extends AsymmetricEncryption
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RsaSign.class);

    public RsaSign(File privateFile, File publicFile)
    {
        super(privateFile, publicFile);
    }

    public RsaSign(String privateKey, String publicKey)
    {
        super(privateKey, publicKey);
    }


    @Override
    public String decrypt(String content)
    {
        throw new CommonsCoreException("不支持调用");
    }

    @Override
    public String encrypt(String content, String charset)
    {
        try
        {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(content.getBytes());
            byte[] result = signature.sign();
            return Base64.encodeBase64String(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("加签失败");
        }
    }

    /**
     * 验签
     * @param content
     * @param sign
     * @return
     */
    public boolean verify(String content, String sign)
    {
        try
        {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(content.getBytes());
            return signature.verify(Base64.decodeBase64(sign));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("加签异常");
        }
    }
}
