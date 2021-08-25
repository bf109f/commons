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
public class VoiceMessage extends BaseMessage
{
    /**
     * 语音格式，如amr，speex等
     */
    @XmlElement(name = "Format")
    private String format;

    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    @XmlElement(name = "MediaId")
    private String mediaId;

    /**
     * 语音识别结果，UTF8编码
     */
    @XmlElement(name = "Recognition")
    private String recognition;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
