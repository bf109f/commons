package com.example.commoncustomizecore.api.weChat.officialaccounts.util;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.utils.AssertUtil;
import com.example.commoncustomizecore.api.weChat.officialaccounts.model.SignInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return signInfo.getSignature().equals(shaContent);
    }

    /**
     *  输入流转换成字符串
     * @param is
     * @return
     */
    public static String inputStream2string(InputStream is)
    {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            byte[] data = new byte[10240];
            int len = 0;

            while ((len = is.read(data)) != -1)
            {
                baos.write(data, 0, len);
            }

            if (baos.size() > 0)
            {
                return baos.toString();
            }
        } catch (IOException e)
        {
            LOG.error(e.getMessage(), e);
        } finally
        {
            try
            {
                baos.close();
                baos.flush();
                is.close();
            } catch (IOException e)
            {

            }
        }
        return "";
    }

    /**
     * 对参数值进行排序
     * @param signInfo
     * @return
     */
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

            byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
            byte[] md5Bytes = sha.digest(byteArray);
            StringBuilder hexValue = new StringBuilder();
            for (int i = 0; i < md5Bytes.length; i++)
            {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException(e.getMessage());
        }
    }

    public static String getMessageType(String message)
    {
        AssertUtil.isBlank(message);
        String reg = "<MsgType>(.*?)</MsgType>";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find())
        {
            String str = matcher.group(1);
            String subReg = "\\<\\!\\[CDATA\\[(.*?)\\]\\]\\>";
            Pattern subPattern = Pattern.compile(subReg);
            Matcher subMatcher = subPattern.matcher(str);

            System.out.println(subMatcher.group(1));
        }
        return null;
    }

}
