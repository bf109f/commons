package com.example.commoncustomizecore.api.encryption.security;

import com.example.commoncustomizecore.api.encryption.security.base.SymmetricEncryption;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
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
            SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decodeBase64(key), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return Base64.encodeBase64String(cipher.doFinal(content.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("AES解密失败");
        }

    }

    @Override
    public String encrypt(String content)
    {
        try
        {
            checkKey(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decodeBase64(key), ALGORITHM);
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
