package com.hxg.api.weChat.officialaccounts.model.xmlbean;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class LinkMessage extends BaseMessage
{
    /**
     * 消息标题
     */
    @XmlElement(name = "Title")
    private String title;

    /**
     * 消息描述
     */
    @XmlElement(name = "Description")
    private String description;

    /**
     * 消息链接
     */
    @XmlElement(name = "Url")
    private String url;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
