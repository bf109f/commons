package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.SymmetricEncryption;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * DES加密
 */
public class Des extends SymmetricEncryption
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Des.class);

    private static final String ALGORITHM = "DES";

    /**
     * 加密密钥 该密钥需要base64编码
     * @param key
     */
    public Des(String key)
    {
        super(key);
    }

    @Override
    public String decrypt(String content)
    {
        try
        {
            SecretKey mesKey = getKey(key);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, mesKey);
            return new String(cipher.doFinal(Base64.decodeBase64(content)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("DES解密失败");
        }
    }

    @Override
    public String encrypt(String content, String charset)
    {
        try
        {
            SecretKey desKey = getKey(key);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            return Base64.encodeBase64String(cipher.doFinal(content.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("DES加密失败");
        }
    }

    /**
     * 获取对称密钥
     * @param key base64编码后的密钥字符串
     * @return
     */
    private SecretKey getKey(String key)
    {
        checkKey(key);
        byte [] decodeKey = Base64.decodeBase64(key);
        if (decodeKey.length < 8)
        {
            throw new CommonsCoreException("des密钥字节长度小于8，传入长度为[" + decodeKey.length + "]");
        }
        try
        {
            DESKeySpec desKeySpec = new DESKeySpec(Base64.decodeBase64(key));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            return secretKeyFactory.generateSecret(desKeySpec);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("DES key初始化失败");
        }
    }
}
