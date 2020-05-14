package com.example.commoncustomizecore.api.httputils;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils
{
    private  static  final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * httpClient发送get请求
     * @param url 请求地址
     * @param param 请求参数
     * @return
     */
    public static String sendGet(String url, String param)
    {
        if (StringUtils.isBlank(url))
        {
            throw new CommonsCoreException("httpClient: url不能为空");
        }

        String rsp = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet get = new HttpGet(url + param);
        CloseableHttpResponse response = null;
        try
        {
            response = httpClient.execute(get);
            if (response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                rsp = EntityUtils.toString(entity, "UTF-8");
            }
            return rsp;
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("httpClient请求异常");
        } finally
        {
            try
            {
                httpClient.close();
                if (response != null)
                {
                    response.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * httpClient发送post请求
     * @param requestData 请求参数
     * @param url 请求地址
     * @return
     */
    public static String sendPost(List<NameValuePair> requestData, String url)
    {
        if (StringUtils.isBlank(url))
        {
            throw new CommonsCoreException("httpClient: url不能为空");
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try
        {
            HttpEntity reqEntity = new UrlEncodedFormEntity(requestData, "utf-8");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                    .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                    .setConnectionRequestTimeout(5000)
                    .build();
            // 构造httpClient对象
            httpClient = HttpClients.createDefault();
            // 构造httpPost对象
            HttpPost post = new HttpPost(url.trim());
            post.setEntity(reqEntity);
            post.setConfig(requestConfig);

            String rsp = null;
            response = httpClient.execute(post);
            if (response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                rsp = EntityUtils.toString(entity, "UTF-8");
            } else
            {
                throw new CommonsCoreException(String.valueOf(response.getStatusLine().getStatusCode()));
            }

            return rsp;
        } catch (UnsupportedEncodingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("httpClient请求异常");
        } catch (ClientProtocolException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("httpClient请求异常");
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("httpClient请求异常");
        } finally
        {
            if (httpClient != null)
            {
                try
                {
                    httpClient.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (response != null)
            {
                try
                {
                    response.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * map转pair list
     * @param params map
     * @return list
     */
    public static List<NameValuePair> map2List(Map<String, String> params)
    {
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet())
        {
            if (StringUtils.isNotBlank(entry.getValue().toString()))
            {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString().trim());
                pairList.add(pair);
            }
        }
        return pairList;
    }
}
