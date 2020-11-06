package com.example.commoncustomizecore.api.encryption;

import com.example.commoncustomizecore.api.constants.SecureConstant;
import com.example.commoncustomizecore.api.encryption.factory.SecureFactory;
import com.example.commoncustomizecore.api.encryption.secure.*;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.utils.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SecurityExecute
{
    private static final Logger LOG = LoggerFactory.getLogger(SecurityExecute.class);

    /**
     * 字符集
     */
    private String charset = "UTF-8";

    public SecurityExecute()
    {

    }

    /**
     *
     * @param charset 字符集
     */
    public SecurityExecute(String charset)
    {
        this.charset = charset;
    }

    /**
     * md5编码
     * @return
     */
    public String md5(String content)
    {
        AssertUtil.isEmpty(content, "编码内容不能为空");
        try
        {
            byte[] data = content.getBytes();
            MD5Codec codec = (MD5Codec) SecureFactory.getCodec(SecureConstant.MD5, null);
            return codec.getEncryptForHex(data);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("md5编码失败");
        }
    }

    /**
     * sha编码(单向加密)
     * @return
     */
    public String sha(String content)
    {
        AssertUtil.isEmpty(content, "编码内容不能为空");
        try
        {
            byte[] data = content.getBytes();
            SHACodec codec = (SHACodec)SecureFactory.getCodec(SecureConstant.SHA, null);
            return codec.getEncryptForHex(data);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("sha编码失败");
        }
    }

    /**
     * 对内容加密
     * @return key为 secretKey(密钥)，encryptData(加密数据)的map
     */
    public Map<String, String> desEncrypt(String content)
    {
        AssertUtil.isEmpty(content, "加密内容不能为空");
        try
        {
            byte[] data = content.getBytes();
            DESCodec codecA = (DESCodec)SecureFactory.getCodec(SecureConstant.DES, null);
            String secretKey = codecA.getSecretKey();
            byte[] encryptData = codecA.encrypt(data);
            Map<String, String> encryptInfo = new HashMap<>();
            encryptInfo.put("secretKey", secretKey);
            encryptInfo.put("encryptData", Base64.getEncoder().encodeToString(encryptData));
            return encryptInfo;
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("DES加密失败");
        }
    }

    /**
     * des加密
     * @return
     */
    public String desEncrypt(String content, String key)
    {
        AssertUtil.isBlank(key, "DES加密密钥不能为空");
        AssertUtil.isEmpty(content, "加密内容不能为空");

        try
        {
            byte[] data = content.getBytes();
            DESCodec codecA = (DESCodec)SecureFactory.getCodec(SecureConstant.DES, key);
            byte[] encryptData = codecA.encrypt(data);
            return Base64.getEncoder().encodeToString(encryptData);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("DES加密失败");
        }
    }

    /**
     * des解密
     * @param encryptString base64编码的秘文字符串
     * @param key base64编码的密钥
     * @return
     */
    public String desDecrypt(String encryptString, String key)
    {
        AssertUtil.isBlank(key, "密钥不能为空");
        AssertUtil.isEmpty(encryptString, "解密内容不能为空");
        try
        {
            DESCodec codecB = (DESCodec)SecureFactory.getCodec(SecureConstant.DES, key);
            byte[] decryptData = codecB.decrypt(Base64.getDecoder().decode(encryptString));
            return new String(decryptData);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("DES解密失败");
        }
    }

    /**
     * AES加密
     * @param content 加密明文
     * @return key为 secretKey(密钥)，encryptData(加密数据)的map
     */
    public Map<String, String> aesEncrypt(String content)
    {
        AssertUtil.isEmpty(content, "加密内容不能为空");
        try
        {
            byte[] data = content.getBytes();
            AESCodec codecA = (AESCodec)SecureFactory.getCodec(SecureConstant.AES, null);
            byte[] encryptData = codecA.encrypt(data);
            Map<String, String> encryptInfo = new HashMap<>();
            encryptInfo.put("secretKey", codecA.getSecretKey());
            encryptInfo.put("encryptData", Base64.getEncoder().encodeToString(encryptData));
            return encryptInfo;
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("AES加密失败");
        }
    }

    /**
     * ase加密
     * @return
     */
    public String aesEncrypt(String content, String key)
    {
        AssertUtil.isBlank(key, "AES加密密钥不能为空");
        AssertUtil.isEmpty(content, "内容不能为空");

        try
        {
            byte[] data = content.getBytes();
            AESCodec codecA = (AESCodec)SecureFactory.getCodec(SecureConstant.AES, key);
            byte[] encryptData = codecA.encrypt(data);
            return Base64.getEncoder().encodeToString(encryptData);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("AES加密失败");
        }
    }

    /**
     * aes解密
     * @return
     */
    public String aesDecrypt(String encryptString, String key)
    {
        AssertUtil.isBlank(key, "AES解密密钥不能为空");
        AssertUtil.isEmpty(encryptString, "内容不能为空");
        try
        {
            AESCodec codecB = (AESCodec)SecureFactory.getCodec(SecureConstant.AES, key);
            byte[] decryptData = codecB.decrypt(Base64.getDecoder().decode(encryptString));
            return new String(decryptData);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("AES解密失败");
        }
    }

    /**
     * rsa公钥加签
     * @param content
     * @param privateKey
     * @return
     */
    public String rsaSignByPrivate(String content, String privateKey)
    {
        AssertUtil.isEmpty(content, "签名原文不能为空");
        AssertUtil.isBlank(privateKey, "密钥不能为空");

        try
        {
            byte[] data = content.getBytes(charset);
            RSAForPrivateCodec codecA = (RSAForPrivateCodec)SecureFactory.getCodec(SecureConstant.RSA_PRIVATE, privateKey);
            String sign = codecA.sign(data);
            return sign;
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("rsa加签失败");
        }
    }

    /**
     * 公钥验签
     * @param content 签名原文
     * @param sign 签名
     * @param publicKey 公钥
     * @return
     */
    public boolean rsaVerifySignByPublic(String content, String sign, String publicKey)
    {
        AssertUtil.isEmpty(content, "签名原文为空");
        AssertUtil.isBlank(sign, "签名为空");
        AssertUtil.isBlank(publicKey, "密钥为空");
        try
        {
            RSAForPublicCodec codecB = (RSAForPublicCodec)SecureFactory.getCodec(SecureConstant.RSA_PUBLIC, publicKey);
            return codecB.verifySign(content.getBytes(), sign);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("rsa验签失败");
        }
    }

    /**
     * rsa公钥加密
     * @param content 加密原文
     * @param publicKey 加密公钥
     * @return
     */
    public String rsaEncryptByPublic(String content, String publicKey)
    {
        AssertUtil.isEmpty(content, "加密内容为空");
        AssertUtil.isBlank(publicKey, "加密公钥为空");
        try
        {
            RSAForPublicCodec codecB = (RSAForPublicCodec)SecureFactory.getCodec(SecureConstant.RSA_PUBLIC, publicKey);
            byte [] encryptData = codecB.encrypt(content.getBytes());
            return Base64.getEncoder().encodeToString(encryptData);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("rsa加密失败");
        }
    }

    /**
     * 私钥加密
     * @param content 明文
     * @param privateKey 私钥
     * @return
     */
    public String rsaEncryptByPrivate(String content, String privateKey)
    {
        AssertUtil.isEmpty(content, "加密内容为空");
        AssertUtil.isBlank(privateKey, "加密私钥为空");
        try
        {
            RSAForPrivateCodec codecA = (RSAForPrivateCodec)SecureFactory.getCodec(SecureConstant.RSA_PRIVATE, privateKey);
            byte [] encryptData = codecA.encrypt(content.getBytes());
            return Base64.getEncoder().encodeToString(encryptData);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("rsa加密失败");
        }
    }

    /**
     * 公钥解密
     * @param encryptString 加密字符串
     * @param publicKey 公钥
     * @return
     */
    public String rsaDecryptByPublic(String encryptString, String publicKey)
    {
        AssertUtil.isEmpty(encryptString, "解密内容为空");
        AssertUtil.isBlank(publicKey, "解密公钥为空");
        try
        {
            RSAForPublicCodec codecB = (RSAForPublicCodec)SecureFactory.getCodec(SecureConstant.RSA_PUBLIC, publicKey);
            byte [] encryptData = codecB.decrypt(Base64.getDecoder().decode(encryptString));
            return new String(encryptData);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("rsa解密失败");
        }
    }

    /**
     * rsa 私钥解密
     * @param encryptString 秘文
     * @param privateKey 私钥
     * @return
     */
    public String rsaDecryptByPrivate(String encryptString, String privateKey)
    {
        AssertUtil.isEmpty(encryptString, "解密内容为空");
        AssertUtil.isBlank(privateKey, "解密公钥为空");
        try
        {
            RSAForPrivateCodec codecA = (RSAForPrivateCodec)SecureFactory.getCodec(SecureConstant.RSA_PRIVATE, privateKey);
            byte [] encryptData = codecA.decrypt(Base64.getDecoder().decode(encryptString));
            return new String(encryptData);
        } catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException("rsa解密失败");
        }
    }


}
