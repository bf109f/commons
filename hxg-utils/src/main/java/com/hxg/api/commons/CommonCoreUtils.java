package com.hxg.api.commons;

import com.hxg.api.commons.inner.CommonInner;
import com.hxg.api.commons.inner.IdWorker;
import com.hxg.api.constants.CommonConstant;
import com.hxg.api.exception.CommonsCoreException;
import com.hxg.api.utils.AssertUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonCoreUtils extends CommonInner
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCoreUtils.class);

    public static String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * BufferedReader 转字符串
     * @param reader BufferedReader
     * @return
     */
    public static String buffer2string(BufferedReader reader)
    {
        try
        {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (StringUtils.isNotBlank(line))
            {
                sb.append(line);
                line = reader.readLine();
            }
            return sb.toString();
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("IO异常");
        }
    }

    /**
     * path路径分段地址连接
     * @param paths
     * @param type
     * @see CommonConstant
     * @return
     */
    public static String connectPathOrUrl(String type, String... paths)
    {
        if (StringUtils.isBlank(type))
        {
            throw new CommonsCoreException("连接类型不能为空");
        }
        if (paths.length == 0)
        {
            throw new CommonsCoreException("url段不能为空");
        }

        LOGGER.debug("路径地址列表 = {}", Arrays.toString(paths));
        if (paths.length == 1)
        {
            return ArrayUtils.toString(paths);
        }
        if (CommonConstant.CONNECT_TYPE_URL.equals(type))
        {
            return connectUrl(paths);
        } else
        {
            return connectPath(paths);
        }
    }

    /**
     * 根据文件路径获取文件
     * @param path
     * @return
     */
    public static File getFileByPath(String path)
    {
        if (StringUtils.isBlank(path))
        {
            throw new CommonsCoreException("文件目录路径为空");
        }
        File file = new File(path);
        if (!file.getParentFile().exists())
        {
            AssertUtil.isTrue(file.getParentFile().mkdirs(), "创建文件夹失败");
        }
        return file;
    }

    /**
     * 根据正则表达式匹配信息
     * @param regex 正则表达式
     * @param content 字符串内容
     * @return 匹配到到字符串列表
     */
    public static List<String> matchByRegex(String regex, String content)
    {
        return matchByRegex(regex, content, -1);
    }

    /**
     * 根据正则表达式匹配信息
     * @param regex 正则表达式
     * @param content 字符串内容
     * @param limit 匹配次数 负数全部匹配
     * @return 匹配到到字符串列表
     */
    public static List<String> matchByRegex(String regex, String content, int limit)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        int count = 1;
        List<String> list = new ArrayList<>();
        while (matcher.find())
        {
            String str = matcher.group(1);
            list.add(str);
            if (limit > 0)
            {
                if (count > limit)
                    break;
                count ++;
            }
        }
        return list;
    }

    /**
     * 获取参数校验信息
     * @param validationMessage ValidationException 返回信息
     * @return
     */
    public static String getValidationMessage(String validationMessage)
    {
        AssertUtil.isBlank(validationMessage, "参数校验失败");
        String reg = "\\{(.*?)}";
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

    /**
     * 判断字符串是否被base64编码
     * @param str
     * @return
     */
    public static boolean isBase64(String str)
    {
        if (StringUtils.isBlank(str))
            return true;
        String decrypt = new String(Base64.decodeBase64(str));
        String base64 = Base64.encodeBase64String(decrypt.getBytes());
        return str.equals(base64);
    }

    /**
     * 对象序列化成字符串
     * @param obj
     * @return
     */
    public static String serialize2string(Object obj)
    {
//        ObjectOutputStream oos = null;
//        ByteArrayOutputStream baos = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos))
        {
            //序列化
//            baos = new ByteArrayOutputStream();
//            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return Base64.encodeBase64String(baos.toByteArray());
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 对象序列话为字节
     * @param obj
     * @return
     */
    public static byte [] serialize2array(Object obj)
    {
//        ObjectOutputStream oos = null;
//        ByteArrayOutputStream baos = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos))
        {
            //序列化
//            baos = new ByteArrayOutputStream();
//            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static Object unSerialize(byte[] bytes)
    {
//        ByteArrayInputStream bais = null;
//        ObjectInputStream ois = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais))
        {
            //反序列化
//            bais = new ByteArrayInputStream(bytes);
//            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 序列化成对象
     * @param base64String
     * @return
     */
    public static Object unSerialize(String base64String)
    {
//        ByteArrayInputStream bais = null;
//        ObjectInputStream ois = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decodeBase64(base64String));
             ObjectInputStream ois = new ObjectInputStream(bais))
        {
            //反序列化
//            bais = new ByteArrayInputStream(Base64.decodeBase64(base64String));
//            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /*private static void close(ObjectInputStream ois, ByteArrayInputStream bais)
    {
        if (ois != null)
        {
            try
            {
                ois.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (bais != null)
        {
            try
            {
                bais.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }*/

    /*private static void close(ObjectOutputStream oos, ByteArrayOutputStream baos)
    {
        if (oos != null)
        {
            try
            {
                oos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (baos != null)
        {
            try
            {
                baos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }*/
}
