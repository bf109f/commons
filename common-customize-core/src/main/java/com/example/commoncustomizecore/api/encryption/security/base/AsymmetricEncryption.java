package com.example.commoncustomizecore.api.encryption.security.base;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密 RSA
 */
public abstract class AsymmetricEncryption extends DoubleEncryption
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AsymmetricEncryption.class);

    private static final String ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    protected static final String  SIGNATURE_ALGORITHM = "SHA1withRSA";
    /**
     * 加密算法RSA
     */
    protected static final String  KEY_ALGORITHM       = "RSA";
    /**
     * RSA最大加密明文大小
     */
    protected static final int    MAX_ENCRYPT_BLOCK   = 117;

    /**
     * RSA最大解密密文大小
     */
    protected static final int    MAX_DECRYPT_BLOCK   = 128;


    /**
     * 私钥
     */
    protected PrivateKey privateKey;

    /**
     * 公钥
     */
    protected PublicKey publicKey;


    public AsymmetricEncryption(File privateFile, File publicFile)
    {
        this.privateKey = getPrivate(getRsaKey(privateFile));
        this.publicKey = getPublic(getRsaKey(publicFile));
    }

    public AsymmetricEncryption(String privateKey, String publicKey)
    {
        this.privateKey = getPrivate(getRsaKey(privateKey));
        this.publicKey = getPublic(getRsaKey(publicKey));
    }

    /**
     * 从密钥文件中获取密钥
     * @param file
     * @return
     */
    protected String getRsaKey(File file)
    {
        try
        {
            String key = FileUtils.readFileToString(file, "UTF-8");
            return key.split("-----")[2].replace("\t\n", "");
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("密钥文件读取失败");
        }
    }

    /**
     * 提取密钥
     * @param keyContent
     * @return
     */
    protected String getRsaKey(String keyContent)
    {
        try
        {
            return keyContent.split("-----")[2].replace("\t\n", "");
        } catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("密钥文件读取失败");
        }
    }

    protected PrivateKey getPrivate(String key)
    {
        try
        {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("初始化私钥失败");
        }
    }

    protected PublicKey getPublic(String key)
    {
        try
        {
            X509EncodedKeySpec x509EncoderKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return keyFactory.generatePublic(x509EncoderKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("初始化公钥失败");
        }
    }

    /**
     * 加解密处理
     * @param key
     * @param maxBlock
     * @param data
     * @return
     */
    protected String dealRsaCipher(Key key, int opmode, int maxBlock, byte [] data)
    {
        ByteArrayOutputStream out = null;
        try
        {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(opmode, key);
            int inputLen = data.length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0)
            {
                if (inputLen - offSet > maxBlock)
                {
                    cache = cipher.doFinal(data, offSet, maxBlock);
                } else
                {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxBlock;
            }
            byte[] encryptedData = out.toByteArray();

            return Base64.encodeBase64String(encryptedData);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException
                | NoSuchPaddingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("RSA加解密失败");
        } finally
        {
            try
            {
                if (out != null)
                    out.close();
            } catch (IOException e)
            {

            }
        }
    }
}
