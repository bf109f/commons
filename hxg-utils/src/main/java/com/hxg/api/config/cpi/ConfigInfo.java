package com.hxg.api.config.cpi;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * 单例模式 懒汉 饿汉(jvm类加载) 静态内部类(jvm类加载)
 * jvm类加载
 */
@Data
public class ConfigInfo
{
    private static final Logger LOG = LoggerFactory.getLogger(ConfigInfo.class);

    /**
     * volatile java初始化指令不会重新排序 1 开辟空间 2 初始化 3 赋值  2和3的排序
     * https://www.cnblogs.com/xdecode/p/8948277.html
     */
    private static volatile ConfigInfo configInfo;

    private String connectTimeout;

    private String socketTimeout;

    private String connectionRequestTimeout;

    public ConfigInfo(Properties props)
    {
        this.connectTimeout = props.getProperty(PropertyKey.HTTP_CONNECT_TIME_OUT.getKeyName());
        this.socketTimeout = props.getProperty(PropertyKey.HTTP_SOCKET_TIME_OUT.getKeyName());
        this.connectionRequestTimeout = props.getProperty(PropertyKey.HTTP_CONNECTION_REQUEST_TIME_OUT.getKeyName());
    }

    public ConfigInfo()
    {
        Properties props = loadProps();
        if (props != null)
        {
            this.connectTimeout = props.getProperty(PropertyKey.HTTP_CONNECT_TIME_OUT.getKeyName());
            this.socketTimeout = props.getProperty(PropertyKey.HTTP_SOCKET_TIME_OUT.getKeyName());
            this.connectionRequestTimeout = props.getProperty(PropertyKey.HTTP_CONNECTION_REQUEST_TIME_OUT.getKeyName());
        }
    }

    public static ConfigInfo getInstance()
    {
        if (configInfo == null)
        {
            synchronized (ConfigInfo.class)
            {
                if (configInfo == null)
                {
                    configInfo = new ConfigInfo();
                    // 字节码执行过程
                    // 1、开辟空间
                    // 2、初始化
                    // 3、引用赋值
                }
            }
        }
        return configInfo;
    }

    private static synchronized Properties loadProps()
    {
        Properties props = new Properties();
        InputStream inputStream = null;
        try
        {
            inputStream = new BufferedInputStream(new FileInputStream(new File("customize.properties")));
            props.load(inputStream);
            return props;
        } catch (FileNotFoundException e)
        {
            LOG.info(e.getMessage(), e);
            return null;
        } catch (IOException e)
        {
            LOG.info(e.getMessage(), e);
            return null;
        }
    }

}
