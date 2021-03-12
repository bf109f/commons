package com.example.commoncustomizecore.api.encryption.security.base;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 单向加密
 */
public abstract class SingleEncryption implements SecurityService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SingleEncryption.class);

    /**
     * 前缀
     */
    protected String prefix;

    /**
     * 后缀
     */
    protected String suffix;

    public SingleEncryption()
    {

    }

    public SingleEncryption(String prefix)
    {
        this.prefix = prefix;
    }

    public SingleEncryption(String prefix, String suffix)
    {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    /**
     * 2进制数字转换为16进制字符串
     * @param data
     * @return
     */
    protected String parseByteArray2HexStr(byte[] data)
    {
        if(data == null || data.length < 1)
        {
            return null;
        }

        StringBuilder hex = new StringBuilder();
        for (byte datum : data)
        {
            int h = datum & 0XFF;
            if (h < 16)
            {
                hex.append("0");
            }
            hex.append(Integer.toHexString(h));
        }

        return hex.toString();
    }

    protected String encrypt(String content, String model, String charset)
    {
        try
        {
            if (StringUtils.isBlank(charset))
                charset = "utf-8";
            MessageDigest messageDigest = MessageDigest.getInstance(model);
            return parseByteArray2HexStr(messageDigest.digest((prefix + content + suffix).getBytes(charset)));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException(model + "编码失败");
        }
    }
}
