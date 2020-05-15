package com.example.commoncustomizecore.api.httputils;

import com.example.commoncustomizecore.api.bean.BeanUtils;
import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class HttpUtils
{
    private  static  final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
//    private static final Log LOG = LogFactory.getLog(HttpUtils.class);

    /**
     * httpClient发送get请求
     * @param url 请求地址
     * @param param 请求参数
     * @return
     */
    public static String sendGet(String param, String url)
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

            getTrance(response);

            return rsp;
        } catch (IOException e)
        {
//            LOGGER.error(e.getMessage(), e);
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
     * httpClient发送post请求 application/x-www-form-urlencoded
     * @param requestData 请求参数 格式 key=value&
     * @param url 请求地址
     * @return
     */
    public static String sendPost(List<NameValuePair> requestData, String url)
    {
        if (StringUtils.isBlank(url))
        {
            throw new CommonsCoreException("httpClient: url不能为空");
        }

        try
        {
            HttpEntity reqEntity = new UrlEncodedFormEntity(requestData, "utf-8");

            // 构造httpPost对象
            HttpPost post = new HttpPost(url.trim());

            return send(reqEntity, post);
        } catch (UnsupportedEncodingException e)
        {
//            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("httpClient请求异常");
        }
    }

    /**
     * 发送json格式的请求 application/json
     * @param req json字符串
     * @param url 请求地址
     * @return
     */
    public static String sendPost(String req, String url)
    {
        if (StringUtils.isBlank(url))
        {
            throw new CommonsCoreException("httpClient: url不能为空");
        }


        StringEntity reqEntity = new StringEntity(req, "utf-8");

        // 构造httpPost对象
        HttpPost post = new HttpPost(url.trim());
        post.setHeader("Content-Type", "application/json");

        return send(reqEntity, post);

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

    /**
     * 发送请求
     * @param req
     * @param httpPost
     * @param <T>
     * @return
     */
    private static <T extends HttpEntity> String send(T req, HttpPost httpPost)
    {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try
        {
//            StringEntity reqEntity = new StringEntity(requestData, "utf-8");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                    .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                    .setConnectionRequestTimeout(5000)
                    .build();
            // 构造httpClient对象
            httpClient = HttpClients.createDefault();
            // 构造httpPost对象
//            HttpPost post = new HttpPost(url.trim());
//            post.setHeader("Content-Type", "application/json");
            httpPost.setEntity(req);
            httpPost.setConfig(requestConfig);

            String rsp = null;
            response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                rsp = EntityUtils.toString(entity, "UTF-8");
            } else
            {
                throw new CommonsCoreException(String.valueOf(response.getStatusLine().getStatusCode()));
            }
            getTrance(response);
            return rsp;
        } catch (UnsupportedEncodingException e)
        {
//            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("httpClient请求异常");
        } catch (ClientProtocolException e)
        {
//            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("httpClient请求异常");
        } catch (IOException e)
        {
//            LOGGER.error(e.getMessage(), e);
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
     * 日志跟踪号
     * @param response
     */
    private static void getTrance(CloseableHttpResponse response)
    {
        Header [] headers = response.getHeaders("tranceId");
        for (Header header : headers)
        {
            LOGGER.info("name = {}, value = {}", header.getName(), header.getValue());
        }
    }

    /**
     * 将对象转化为list<NameValuePair> 注意 对象的属性需是String Integer Double Float
     * @param obj
     * @return
     */
    public static List<NameValuePair> obj2List(Object obj)
    {
        List<NameValuePair> pairs = new ArrayList<>();
        List<Field> fields = BeanUtils.getDeclaredFields(obj);
        fields.forEach(field -> {
            Object value = BeanUtils.getFieldValue(obj, field.getName());
            if (value == null)
            {
                return; // lamada表达式中跳出当前循环,继续下一次循环,作用类似continue
            }
            else if (value instanceof String)
            {
                NameValuePair pair = new BasicNameValuePair(field.getName(), (String) value);
                pairs.add(pair);
            } else if (value instanceof Integer || value instanceof Double ||
                    value instanceof Float || value instanceof Long)
            {
                NameValuePair pair = new BasicNameValuePair(field.getName(), String.valueOf(value));
                pairs.add(pair);
            }
            else if (value instanceof Date || value instanceof java.sql.Date)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(value);
                NameValuePair pair = new BasicNameValuePair(field.getName(), date);
                pairs.add(pair);
            }
            else
            {
                throw new CommonsCoreException("不支持该类对象");
            }

        });
        return pairs;
    }


}
