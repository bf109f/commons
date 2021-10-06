package com.hxg.api.httputils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hxg.api.bean.BeanUtils;
import com.hxg.api.exception.CommonsCoreException;
import com.hxg.api.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class HttpUtils
{
    private  static  final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
//    private static final Log LOG = LogFactory.getLog(HttpUtils.class);

    /**
     * http发送get请求 参数list NameValuePair
     * @param params 请求参数
     * @param url 请求地址
     * @return
     */
    @Deprecated
    public static String sendGet(List<NameValuePair> params, String url)
    {
        AssertUtil.isBlank(url, "httpClient: url不能为空");
        try
        {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(params);
            HttpGet httpGet = new HttpGet(builder.build());
            return request(httpGet);
        } catch (URISyntaxException e)
        {
            throw new CommonsCoreException("httpClient: " + e.getMessage());
        }
    }

    /**
     * httpClient发送get请求
     * @param url 请求地址
     * @param param 请求参数
     * @return
     */
    public static String sendGet(String param, String url)
    {
        AssertUtil.isBlank(url, "httpClient: url不能为空");

        HttpGet httpGet = new HttpGet(url + "?" + param);
        return request(httpGet);
    }

    /**
     * httpClient发送get请求
     * @param url 请求地址
     * @param obj 请求参数
     * @return
     */
    public static String sendGet(Object obj, String url)
    {
        AssertUtil.isBlank(url, "httpClient: url不能为空");

        try
        {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(obj2List(obj));
            HttpGet httpGet = new HttpGet(builder.build());
            return request(httpGet);
        } catch (URISyntaxException e)
        {
            throw new CommonsCoreException("httpClient: " + e.getMessage());
        }
    }

    /**
     * httpClient发送get请求
     * @param param 请求参数
     * @param url 请求地址
     * @param headers 请求消息头
     * @return
     */
    public static String sendGet(String param, String url, Header...headers)
    {
        AssertUtil.isBlank(url, "httpClient: url不能为空");

        URI uri;
        try
        {
            URIBuilder builder = new URIBuilder(url);
            uri = builder.setCustomQuery(param).build();
        } catch (URISyntaxException e)
        {
            throw new CommonsCoreException("httpClient: " + e.getMessage());
        }
        HttpGet httpGet = new HttpGet(uri);
        
        httpGet.setConfig(RequestConfig.custom().setNormalizeUri(true).build());
        for (Header header : headers)
        {
            httpGet.addHeader(header);
        }

        return request(httpGet);
    }

    /**
     * httpClient发送get请求 无参数
     * @param url 请求地址
     * @return
     */
    public static String sendGet(String url)
    {
        AssertUtil.isBlank(url, "httpClient: url不能为空");

        HttpGet httpGet = new HttpGet(url);
        return request(httpGet);
    }



    /**
     * httpClient发送post请求 application/x-www-form-urlencoded
     * @param requestData 请求参数 格式 key=value&
     * @param url 请求地址
     * @return
     */
    public static String sendPost(List<NameValuePair> requestData, String url)
    {
        AssertUtil.isBlank(url, "httpClient: url不能为空");

        try
        {
            HttpEntity reqEntity = new UrlEncodedFormEntity(requestData, "utf-8");

            // 构造httpPost对象
            HttpPost post = new HttpPost(url.trim());

            return doPost(reqEntity, post);
        } catch (UnsupportedEncodingException e)
        {
            throw new CommonsCoreException("httpClient: " + e.getMessage());
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
        AssertUtil.isBlank(url, "httpClient: url不能为空");


        StringEntity reqEntity = new StringEntity(req, "utf-8");

        // 构造httpPost对象
        HttpPost post = new HttpPost(url.trim());
        post.setHeader("Content-Type", "application/json;charset=UTF-8");

        return doPost(reqEntity, post);

    }

    /**
     * post请求 无参数
     * @param url 请求地址
     * @return
     */
    public static String sendPost(String url)
    {
        AssertUtil.isBlank(url, "httpClient: url不能为空");

        // 构造httpPost对象
        HttpPost post = new HttpPost(url.trim());

        return doPost(new StringEntity("", "utf-8"), post);

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

    /**
     * 发送请求
     * @param req 请求参数
     * @param httpPost http对象
     * @param <T>
     * @return
     */
    private static <T extends HttpEntity> String doPost(T req, HttpPost httpPost)
    {
        //            StringEntity reqEntity = new StringEntity(requestData, "utf-8");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();
        // 构造httpClient对象

        // 构造httpPost对象
//            HttpPost post = new HttpPost(url.trim());
//            post.setHeader("Content-Type", "application/json");
        httpPost.setEntity(req);
        httpPost.setConfig(requestConfig);
        return request(httpPost);
    }



    /**
     * 发送http请求
     * @param httpInfo http对象
     * @param <T>HttpRequestBase
     * @return
     */
    private static <T extends HttpRequestBase> String request(T httpInfo)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try
        {
            String rsp = null;
            response = httpClient.execute(httpInfo);
            if (response != null)
            {
                getTrance(response);
                HttpEntity entity = response.getEntity();
                String contentType = entity.getContentType().getValue();
                if (StringUtils.isNotBlank(contentType) && contentType.contains("image"))
                {
                    // 将图片保存转化为base64字符串
                    rsp = Base64.getEncoder().encodeToString(EntityUtils.toByteArray(entity));
                } else
                {
                    rsp = EntityUtils.toString(entity, "UTF-8");
                }

                if (response.getStatusLine().getStatusCode() == 200)
                {
                    return rsp;
                } else
                {
                    JSONObject jsonObject = JSON.parseObject(rsp);
                    LOGGER.error(rsp);
                    throw new CommonsCoreException(String.valueOf(response.getStatusLine().getStatusCode()),
                            jsonObject.getString("error"));
                }

            } else
            {
                throw new CommonsCoreException("httpClient: response is null");
            }
        } catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new CommonsCoreException("httpClient: " + e.getMessage());
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
     * 日志跟踪号
     * @param response
     */
    private static void getTrance(CloseableHttpResponse response)
    {
        Header [] headers = response.getHeaders("tranceId");
        for (Header header : headers)
        {
            LOGGER.info("tranceId = {}", header.getValue());
        }
    }




}
