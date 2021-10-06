package com.hxg.api.utils;

import com.hxg.api.exception.CommonsCoreException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static String getRsaKey(String fileName)
    {
        AssertUtil.isBlank(fileName, "文件名为空");

        try
        {
            URL url1 = FileUtil.class.getClassLoader().getResource(fileName);
            String key = FileUtils.readFileToString(new File(url1.getFile()), "UTF-8");
            if (StringUtils.isBlank(key) || !key.contains("-----"))
            {
                throw new CommonsCoreException("密钥文件为空或密钥文件格式不正确");
            }
            key = key.split("-----")[2].replace("\t\n", "");
            return key;
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException(e.getMessage());
        } catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException(e.getMessage());
        }
    }
}
