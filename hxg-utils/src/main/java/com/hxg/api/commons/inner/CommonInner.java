package com.hxg.api.commons.inner;

import com.hxg.api.exception.CommonsCoreException;

import java.io.File;
import java.util.StringJoiner;

public class CommonInner
{
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
