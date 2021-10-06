package com.hxg.api.encryption.security.base;

import com.hxg.api.commons.CommonCoreUtils;
import com.hxg.api.exception.CommonsCoreException;
import org.apache.commons.lang3.StringUtils;

/**
 * 对称加密 AES DES
 */
public abstract class SymmetricEncryption extends DoubleEncryption
{
    /**
     * 加密密钥 该密钥需要base64编码
     */
    protected String key;

    public SymmetricEncryption(String key)
    {
        this.key = key;
    }

    protected void checkKey(String key)
    {
        if (StringUtils.isBlank(key))
        {
            throw new CommonsCoreException("密钥不能为空");
        }
        if (!CommonCoreUtils.isBase64(key))
        {
            throw new CommonsCoreException("key需要base64编码");
        }
    }
}
