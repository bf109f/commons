package com.hxg.sms.api;

import com.hxg.sms.exception.SmsException;
import com.hxg.sms.model.req.SmsBaseReq;
import com.hxg.sms.model.rsp.SmsBaseRsp;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractSmsApiService<P extends SmsBaseRsp, Q extends SmsBaseReq> implements SmsApiService
{
    // https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints-simple
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     *
     * @param req
     * @param url
     * @return
     */
    protected P request(Q req, String url, Class<P> pClass) {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
        Set<ConstraintViolation<Q>> constraintViolations = validator.validate(req);
        request(req, url, pClass, new Header[]{});
        return null;
    }

    /**
     *
     * @param req
     * @param url 请求url
     * @return
     */
    protected P request(Q req, String url, Class<P> pClass, Header...httpHeaders) {
        String params = prepareParams(req);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeaders(httpHeaders);
        //获取消息头
        List<Header> headers = Arrays.stream(httpHeaders)
                        .filter(v-> StringUtils.equals(v.getName(), HttpHeaders.CONTENT_TYPE))
                        .collect(Collectors.toList());
        if (headers.size() > 0) {
            httpPost.setEntity(new StringEntity(params, headers.get(0).getValue()));
        }
        else {
            httpPost.setEntity(new StringEntity(params, ContentType.APPLICATION_JSON));
        }
        this.request(httpPost, pClass);
        return null;
    }

    /**
     *
     * @param request
     * @return
     */
    private P request(HttpRequestBase request, Class<P> pClass) {
        try
        {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(request);
            P rsp = pClass.getDeclaredConstructor().newInstance();

        } catch (IOException e)
        {

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            throw new SmsException();
        }
        return null;
    }

    /**
     * 处理请求参数 将请求参数加密
     * @param req
     * @return
     */
    protected abstract String prepareParams(Q req);
}
