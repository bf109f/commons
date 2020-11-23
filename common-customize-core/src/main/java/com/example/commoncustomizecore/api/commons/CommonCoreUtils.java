package com.example.commoncustomizecore.api.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonCoreUtils
{
    public static String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取参数校验信息
     * @param validationMessage ValidationException 返回信息
     * @return
     */
    public static String getValidationMessage(String validationMessage)
    {
        if (StringUtils.isBlank(validationMessage))
        {
            return "参数校验失败";
        }
        String reg = "\\{(.*?)\\}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(validationMessage);
        List<Map<String, String>> maps = new ArrayList<>();
        while (matcher.find())
        {
            String str = matcher.group(1);
            if (StringUtils.isNotBlank(str) && str.contains(",") && str.contains("="))
            {
                String [] keyValues = str.split(",");
                Map<String, String> map = new HashMap<>();
                for (String keyValue : keyValues)
                {
                    String [] keys = keyValue.split("=");
                    map.put(keys[0].trim(), keys[1]);
                }
                maps.add(map);
            }
        }
        StringJoiner messages = new StringJoiner(",", "[", "]");
        if (maps.size() > 0)
        {
            for (Map<String, String> map : maps)
            {
                messages.add(map.get("messageTemplate"));
            }
        } else
        {
            messages.add("参数校验失败");
        }
        return messages.toString();
    }

    /**
     * IdWorker 单例防止出现重复
     * @Title: getSnowflakeId
     * @Description: TODO(根据雪花算法计算id)
     * @return    参数
     * @return String    返回类型
     */
    public static String getSnowflakeId(String prefix)
    {
        IdWorker idWorker = IdWorker.getInstance();
        long id = idWorker.nextId();
        return prefix + String.valueOf(id);
    }

    public static String getSnowflakeId()
    {
        IdWorker idWorker = IdWorker.getInstance();
        long id = idWorker.nextId();
        return String.valueOf(id);
    }
}
