package com.example.commoncustomizecore.api.encryption.security.base;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
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
}
