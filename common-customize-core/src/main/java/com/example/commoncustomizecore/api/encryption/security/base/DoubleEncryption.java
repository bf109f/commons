package com.example.commoncustomizecore.api.encryption.security.base;

/**
 * 双向加密
 */
public abstract class DoubleEncryption implements SecurityService
{
    /**
     * 解密
     * @param content 加密内容
     * @return 解密内容
     */
    public abstract String decrypt(String content);
}
