package com.test.sftp.core.api.pool;

public interface SftpSourceBean
{
    /**
     * 登陆超时时间
     * @return
     */
    int getLoginTimeout();

    /**
     * 登陆用户名
     * @return
     */
    String getUserName();

    /**
     * 主机IP地址
     * @return
     */
    String getHost();

    /**
     * 主机端口号
     * @return
     */
    int getPort();


}
