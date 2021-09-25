package com.example.commoncustomizecore.api.commons.detail;

import com.example.commoncustomizecore.api.constants.CommonConstant;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.tianapi.model.NewsInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

public class CommonDetail
{

    protected static List<String> getHolidayOrRestDays(List<NewsInfo> newsInfos, String type)
    {
        if (CollectionUtils.isNotEmpty(newsInfos))
        {
            List<String> list = new ArrayList<>();
            for (NewsInfo newsInfo : newsInfos)
            {
                List<String> days;
                if (CommonConstant.HOLIDAYS.equals(type))
                {
                    days = getDays(newsInfo.getVacation());
                    if (CollectionUtils.isEmpty(days))
                        throw new CommonsCoreException("节假日为空:" + type);
                } else
                {
                    days = getDays(newsInfo.getRemark());
                }
                if (CollectionUtils.isNotEmpty(days))
                    list.addAll(days);

            }
            return list;
        }
        return null;
    }


    private static List<String> getDays(String days)
    {
        if (StringUtils.isNotBlank(days))
        {
            if (days.contains("|"))
            {
                String [] arr = days.split("\\|");
                return Arrays.asList(arr);
            }
            return Collections.singletonList(days);
        }
        return null;
    }

    /**
     * url分段地址连接
     * @param urls
     * @return
     */
    protected static String connectUrl(String... urls)
    {
        StringJoiner joiner = new StringJoiner("/");
        for (int i = 0; i < urls.length; i++)
        {
            if (i == 0)
            {
                if (!(urls[i].startsWith("http") || urls[i].startsWith("https")))
                {
                    throw new CommonsCoreException("url不是以http或https开头的");
                }
                if (urls[i].endsWith("/"))
                {
                    joiner.add(urls[i].substring(0, urls[i].length() - 1));
                } else
                {
                    joiner.add(urls[i]);
                }
            } else
            {
                if (urls[i].startsWith("/"))
                {
                    urls[i] = urls[i].substring(1);
                }
                if (urls[i].endsWith("/"))
                {
                    urls[i] = urls[i].substring(0, urls[i].length() -1);
                }
                joiner.add(urls[i]);
            }
        }
        return joiner.toString();
    }

    /**
     * 路径分段地址连接
     * @param paths
     * @return
     */
    protected static String connectPath(String... paths)
    {
        StringJoiner joiner = new StringJoiner(File.separator);
        for (int i = 0; i < paths.length; i++)
        {
            if (File.separator.equals("/"))
            {
                paths[i] = paths[i].replace("\\", "/");
            } else
            {
                paths[i] = paths[i].replace("/", "\\");
            }
            if (i == 0)
            {
                if (paths[i].endsWith(File.separator))
                {
                    joiner.add(paths[i].substring(0, paths[i].length() - 1));
                } else
                {
                    joiner.add(paths[i]);
                }
            } else
            {
                if (paths[i].startsWith(File.separator))
                {
                    paths[i] = paths[i].substring(1);
                }
                if (paths[i].endsWith(File.separator))
                {
                    paths[i] = paths[i].substring(0, paths[i].length() -1);
                }
                joiner.add(paths[i]);
            }
        }
        return joiner.toString();
    }
}
