package com.example.commoncustomizecore.api.exception;

import lombok.Data;

@Data
public class CommonsCoreException extends RuntimeException
{
    /**
     * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
     */

    private static final long serialVersionUID = 4197752648899743507L;

    /**
     * 错误码
     */
    private String resultCode;

    /**
     * 错误描述
     */
    private String message;

    public CommonsCoreException(String resultCode, String message)
    {
        super();
        this.resultCode = resultCode;
        this.message = message;
    }

    public CommonsCoreException(String resultCode)
    {
        super();
        this.resultCode = resultCode;
    }
}
