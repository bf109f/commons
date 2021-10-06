package com.hxg.api.encryption.security;

import com.hxg.api.encryption.security.base.SymmetricEncryption;
import com.hxg.api.exception.CommonsCoreException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES加密
 */
public class Aes extends SymmetricEncryption
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Aes.class);

    private static final String ALGORITHM = "AES";

//    private static final int KEY_SIZE = 128;


    /**
     * 加密密钥 该密钥需要base64编码
     * @param key
     */
    public Aes(String key)
    {
        super(key);
    }

    @Override
    public String decrypt(String content)
    {
        try
        {
            checkKey(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(getKeyByte(key), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("AES解密失败");
        }

    }

    @Override
    public String encrypt(String content, String charset)
    {
        try
        {
            checkKey(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(getKeyByte(key), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.encodeBase64String(cipher.doFinal(content.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("AES加密失败");
        }
    }

    private byte [] getKeyByte(String key)
    {
        byte [] keys = Base64.decodeBase64(key);
        if (keys.length != 16 && keys.length != 32)
        {
            throw new CommonsCoreException("AES key必须是16或32位");
        }
        return keys;
    }

    /*private void initKey()
    {
        try
        {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(KEY_SIZE,secureRandom);
            SecretKey aesKey = keyGenerator.generateKey();
            key = Base64.encodeBase64String(aesKey.getEncoded());
        } catch (NoSuchAlgorithmException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("AES初始化失败");
        }
    }*/


}
