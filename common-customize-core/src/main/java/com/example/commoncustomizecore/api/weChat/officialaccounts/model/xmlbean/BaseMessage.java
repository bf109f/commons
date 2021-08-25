package com.example.commoncustomizecore.api.weChat.officialaccounts.model.xmlbean;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseMessage
{
    /**
     * 开发者微信号
     */
    @XmlElement(name = "ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @XmlElement(name = "CreateTime")
    private long createTime;

    /**
     * 消息类型，event
     */
    @XmlElement(name = "MsgType")
    private String msgType;

    /**
     * 消息ID
     */
    @XmlElement(name = "MsgId")
    private String msgId;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
