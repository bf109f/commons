package com.example.commoncustomizecore.test;

import com.example.commoncustomizecore.api.commons.CommonCoreUtils;
import com.example.commoncustomizecore.api.encryption.SecurityExecute;
import com.example.commoncustomizecore.api.encryption.security.*;
import com.example.commoncustomizecore.api.utils.FileUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Map;

public class SecurityExecuteTest
{
    private static String str = "川建国没戏了ssss";

    private static String key = "01286700";

//    @Test
    public void isBase64()
    {
        String str = "YWNzZGVzbWl5YW8=";
        String decrypt = new String(Base64.getDecoder().decode(str.getBytes()));
        String base64 = Base64.getEncoder().encodeToString(decrypt.getBytes());
        System.out.println(base64.equals(str));
        System.out.println(CommonCoreUtils.isBase64(str));
    }

//    @Test
    public void testDes()
    {
        System.out.println(key.length());
        SecurityExecute execute = new SecurityExecute();
        String encrypt = execute.desEncrypt(str, key);
        System.out.println(encrypt);
        String decrypt = execute.desDecrypt(encrypt, Base64.getEncoder().encodeToString(key.getBytes()));
        System.out.println(decrypt);

        Map<String, String> map = execute.desEncrypt(str);

        map.forEach((key, value) ->
        {
            System.out.println(key + ":" + value);

        });
        String noKey = execute.desDecrypt("5E6mp/1+8cwNN2dMcl+KZnOPoiJybl48", "yOA43MeRHCA=");
        System.out.println(noKey);
    }

//    @Test
    public void testAes()
    {
        SecurityExecute execute = new SecurityExecute();
        System.out.println(key.length());
        String encrypt = execute.aesEncrypt(str, Base64.getEncoder().encodeToString(key.getBytes()));
        System.out.println(encrypt);
        System.out.println(execute.aesDecrypt(encrypt, Base64.getEncoder().encodeToString(key.getBytes())));

        Map map = execute.aesEncrypt(str);
        map.forEach((key, value) ->
        {
            System.out.println(key + ":" + value);
        });
        System.out.println(execute.aesDecrypt("4PFiAD09Wt9NTN04mp02la+alPWyZ5n+7obtqFSNNXo=", "BuJkx+rNbh3OkmEkQEM3qg=="));
    }

//    @Test
    public void testRsaSign()
    {
        SecurityExecute execute = new SecurityExecute();
        try
        {
//            String bastPath = SecurityExecuteTest.class.getClassLoader().getResource("").toString();
            URL url = SecurityExecuteTest.class.getClassLoader().getResource("rsa_private.pem");
            String privateKey = FileUtils.readFileToString(new File(url.getFile()), "UTF-8");
            privateKey = privateKey.split("-----")[2].replace("\t\n", "");
            String sign = execute.rsaSignByPrivate("_input_charset=川建国没戏了ssss&ke=khgs", privateKey);
            System.out.println(sign);

            System.out.println("========验签========");
            URL urlP = SecurityExecuteTest.class.getClassLoader().getResource("rsa_public.pem");
            String publicKey = FileUtils.readFileToString(new File(urlP.getFile()), "UTF-8");
            publicKey = publicKey.split("-----")[2].replace("\t\n", "");
            System.out.println(execute.rsaVerifySignByPublic("_input_charset=川建国没戏了ssss&ke=khgs", sign, publicKey));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 公钥加密
     * 私钥解密
     */
//    @Test
    public void testRsaSecret()
    {
        try
        {
            SecurityExecute execute = new SecurityExecute();
            URL url = SecurityExecuteTest.class.getClassLoader().getResource("rsa_public.pem");
            String privateKey = FileUtils.readFileToString(new File(url.getFile()), "UTF-8");
            privateKey = privateKey.split("-----")[2].replace("\t\n", "");
            String encrypt = execute.rsaEncryptByPublic(str, privateKey);
            System.out.println(encrypt);
            System.out.println("========解密==========");
            URL url1 = SecurityExecuteTest.class.getClassLoader().getResource("rsa_private.pem");
            String publicKey = FileUtils.readFileToString(new File(url1.getFile()), "UTF-8");
            publicKey = publicKey.split("-----")[2].replace("\t\n", "");
            System.out.println(execute.rsaDecryptByPrivate(encrypt, publicKey));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testSHA()
    {
        Md5 md5 = new Md5("redis", "key");

        Sha1 sha = new Sha1();
        String mdsString = md5.encrypt("shfuyeg7837hdh", "");
        System.out.println(mdsString);
        System.out.println(sha.encrypt(mdsString, ""));
        System.out.println("====================");
        Sha1 sha1 = new Sha1("a", "a");
        System.out.println(sha1.encrypt("普通转账交s易sssssssssssssssss", ""));
    }

    @Test
    public void testSha() throws UnsupportedEncodingException
    {
        Sha1 sha = new Sha1();
        System.out.println(sha.encrypt("2c7d7966dfead856ea5f748b186144ea97dfd756", ""));
        System.out.println(DigestUtils.sha1Hex("2c7d7966dfead856ea5f748b186144ea97dfd756".getBytes()));


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();

        }

        byte[] byteArray = "2c7d7966dfead856ea5f748b186144ea97dfd756".getBytes("UTF-8");
        byte[] md5Bytes = md.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        System.out.println(hexValue.toString());

    }

    @Test
    public void testMd5()
    {
        Md5 md5 = new Md5("a", "a");
        System.out.println(md5.encrypt("普通转账交s易", ""));
    }

    @Test
    public void testAes1()
    {
        Aes aes = new Aes("MTIzNDU2Nzg5MDEyMzQ1Ng==");
        String encrypt = aes.encrypt("a普通转账交s易a", "");
        System.out.println(encrypt);
        System.out.println(aes.decrypt(encrypt));
    }

    @Test
    public void testDes1()
    {
        Des des = new Des("MTIzNDU2Nzg=");
        String encrypt = des.encrypt("a普通转账交s易a", "");
        System.out.println(encrypt);
        System.out.println(des.decrypt(encrypt));
    }

    @Test
    public void testRsaP()
    {
        URL publicUrl = SecurityExecuteTest.class.getClassLoader().getResource("rsa_public.pem");
        URL privateUrl = SecurityExecuteTest.class.getClassLoader().getResource("rsa_private.pem");
        RsaPositive positive = new RsaPositive(new File(privateUrl.getFile()), new File(publicUrl.getFile()));
        String encrypt = positive.encrypt("a普通转账交s易a", "");
        System.out.println(encrypt);
        System.out.println(positive.decrypt(encrypt));
    }

    @Test
    public void testRsaR()
    {
        URL publicUrl = SecurityExecuteTest.class.getClassLoader().getResource("rsa_public.pem");
        URL privateUrl = SecurityExecuteTest.class.getClassLoader().getResource("rsa_private.pem");
        RsaReverse rsaReverse = new RsaReverse(new File(privateUrl.getFile()), new File(publicUrl.getFile()));
        String encrypt = rsaReverse.encrypt("a普通转账交s易a", "");
        System.out.println(encrypt);
        System.out.println(rsaReverse.decrypt(encrypt));
    }

    @Test
    public void testSign()
    {
        URL publicUrl = SecurityExecuteTest.class.getClassLoader().getResource("rsa_public.pem");
        URL privateUrl = SecurityExecuteTest.class.getClassLoader().getResource("rsa_private.pem");
        RsaSign rsaSign = new RsaSign(new File(privateUrl.getFile()), new File(publicUrl.getFile()));
        String sign = rsaSign.encrypt("a普通转账交s易a", "");
        System.out.println(sign);
        System.out.println(rsaSign.verify("a普通转账交s易a", sign));
    }

    /**
     * 私钥加密
     * 公钥解密
     */
//    @Test
    public void testRsaSecret2()
    {
        try
        {
            SecurityExecute execute = new SecurityExecute();
            URL url = SecurityExecuteTest.class.getClassLoader().getResource("rsa_private.pem");
            String privateKey = FileUtils.readFileToString(new File(url.getFile()), "UTF-8");
            privateKey = privateKey.split("-----")[2].replace("\t\n", "");
            String encrypt = execute.rsaEncryptByPrivate(str, privateKey);
            System.out.println(encrypt);
            System.out.println("========解密==========");
            /*URL url1 = SecurityExecuteTest.class.getClassLoader().getResource("");
            String publicKey = FileUtils.readFileToString(new File(url1.getFile()), "UTF-8");
            publicKey = publicKey.split("-----")[2].replace("\t\n", "");*/
            String publicKey = FileUtil.getRsaKey("rsa_public.pem");
            System.out.println(execute.rsaDecryptByPublic(encrypt, publicKey));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
