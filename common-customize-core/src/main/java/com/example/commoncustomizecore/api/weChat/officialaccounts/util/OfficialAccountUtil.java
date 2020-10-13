package com.example.commoncustomizecore.api.weChat.officialaccounts.util;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.weChat.officialaccounts.model.SignInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class OfficialAccountUtil
{

    private static final Logger LOG = LoggerFactory.getLogger(OfficialAccountUtil.class);

    /**
     * String token, String timestamp, String nonce, String signature
     * @param signInfo 加签参数
     * @return
     */
    public static boolean checkSignature(SignInfo signInfo)
    {
        // 对集合进行排序
        String shaContent = shaEncode(sort(signInfo));

        if (signInfo.getSignature().equals(shaContent))
        {
            return true;
        } else
        {
            return false;
        }
    }

    private static String sort(SignInfo signInfo)
    {
        String [] strArray = {signInfo.getToken(), signInfo.getTimestamp(), signInfo.getNonce()};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    private static String shaEncode(String inStr)
    {
        try
        {
            MessageDigest sha = MessageDigest.getInstance("SHA");

            byte[] byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = sha.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++)
            {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException(e.getMessage());
        } catch (UnsupportedEncodingException e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException(e.getMessage());
        }
    }

}
