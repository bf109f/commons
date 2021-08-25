package com.example.commoncustomizecore.api.weChat.officialaccounts.model.xmlbean;

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
public class QRCodeEvent extends BaseMessage
{
    /**
     * 事件类型，subscribe
     */
    @XmlElement(name = "Event")
    private String event;

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    @XmlElement(name = "EventKey")
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @XmlElement(name = "Ticket")
    private String ticket;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
