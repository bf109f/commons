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
public class LocationEvent extends BaseMessage
{
    @XmlElement(name = "Event")
    private String event;

    /**
     * 地理位置纬度
     */
    @XmlElement(name = "Latitude")
    private String latitude;

    /**
     * 地理位置经度
     */
    @XmlElement(name = "Longitude")
    private String longitude;

    /**
     * 地理位置精度
     */
    @XmlElement(name = "Precision")
    private String precision;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
