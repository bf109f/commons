package com.example.commoncustomizecore.api.utils;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.lang3.StringUtils;

public class AssertUtil
{
    public static void isBlank(String content)
    {
        if (StringUtils.isBlank(content))
        {
            throw new CommonsCoreException("内容为空");
        }
    }

    public static void isBlank(String content, String message)
    {
        if (StringUtils.isBlank(content))
        {
            throw new CommonsCoreException(StringUtils.isBlank(message)? "内容为空" : message);
        }
    }

    public static void isEmpty(String content)
    {
        if (StringUtils.isEmpty(content))
        {
            throw new CommonsCoreException("内容为空");
        }
    }

    public static void isEmpty(String content, String message)
    {
        if (StringUtils.isEmpty(content))
        {
            throw new CommonsCoreException(StringUtils.isBlank(message)? "内容为空" : message);
        }
    }

    /**
     * 数字比较
     * @param target 目标
     * @param result 实际
     * @param message 错误信息
     */
    public static void isEqual(int target, int result, String message)
    {
        if (target != result)
        {
            throw new CommonsCoreException(message);
        }
    }
}
