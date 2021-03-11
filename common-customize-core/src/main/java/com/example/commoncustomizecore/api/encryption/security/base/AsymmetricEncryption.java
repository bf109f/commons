package com.example.commoncustomizecore.api.encryption.security.base;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * 非对称加密 RSA
 */
public abstract class AsymmetricEncryption extends DoubleEncryption
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AsymmetricEncryption.class);

    /**
     * 私钥
     */
    protected String privateKey;

    /**
     * 公钥
     */
    protected String publicKey;

    public AsymmetricEncryption(String privateKey)
    {
        this.privateKey = privateKey;
    }

    public AsymmetricEncryption(String privateKey, String publicKey)
    {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
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
}
