package com.test.sftp.core.api.pool;

import java.util.Date;
import java.util.List;

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

    /**
     * 初始化大小
     * @return
     */
    int getInitialSize();

    long getConnectCount();

    long getCloseCount();

    long getConnectErrorCount();

    int getPoolingCount();

    long getRecycleCount();

    int getActiveCount();

    long getCreateCount();

    long getDestroyCount();

    int getMaxWaitThreadCount();

    List<String> getActiveConnectionStackTrace();

    long getMaxWait();

    int getMinIdle();

//    int getMaxIdle();

    long getCreateErrorCount();

    int getMaxActive();

    void setMaxActive(int maxActive);

    int getConnectionErrorRetryAttempts();

    String getProperties();

    Date getCreatedTime();

    String getValidConnectionCheckerClassName();
}
