package com.example.commoncustomizecore.api.encryption.security.base;

public interface SecurityService
{
    /**
     * 加密
     * @param content
     * @return
     */
    String encrypt(String content, String charset);
}
