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
public class LocationMessage extends BaseMessage
{
    /**
     * 地理位置纬度
     */
    @XmlElement(name = "Location_X")
    private String locationX;

    /**
     * 地理位置经度
     */
    @XmlElement(name = "Location_Y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    @XmlElement(name = "Scale")
    private String scale;

    /**
     * 地理位置信息
     */
    @XmlElement(name = "Label")
    private String label;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
