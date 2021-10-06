package com.hxg.info;

import com.hxg.api.weChat.officialaccounts.model.SignInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
@Data
public class SubSign implements Serializable
{
    private static final long serialVersionUID = -4371542665355303738L;

    private String subName;

    private SignInfo signInfo;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
