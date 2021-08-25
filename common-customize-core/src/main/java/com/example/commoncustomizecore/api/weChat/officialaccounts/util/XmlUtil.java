package com.example.commoncustomizecore.api.weChat.officialaccounts.util;

import com.example.commoncustomizecore.api.exception.CommonsCoreException;
import com.example.commoncustomizecore.api.weChat.officialaccounts.model.xmlbean.BaseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtil
{
    private static final Logger LOG = LoggerFactory.getLogger(XmlUtil.class);

    public static String obj2Xml(Object obj)
    {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try
        {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException(e.getMessage());
        }
        return sw.toString();
    }

    public static <T extends BaseMessage> T xml2obj(Class<T> clazz, String xmlStr)
    {
        T xmlObject = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = (T) unmarshaller.unmarshal(sr);
        } catch (JAXBException e)
        {
            LOG.error(e.getMessage(), e);
            throw new CommonsCoreException(e.getMessage());
        }
        return xmlObject;
    }
}
