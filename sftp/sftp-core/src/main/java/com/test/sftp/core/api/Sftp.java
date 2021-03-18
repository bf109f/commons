package com.test.sftp.core.api;

public class Sftp implements SftpService
{
    /**
     * 端口号
     */
    private int port;

    /**
     * 主机
     */
    private String host;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密钥
     */
    private String privateKey;

    /**
     * 密钥口令
     */
    private String passphrase;

    public Sftp(int port, String host, String userName, String password)
    {

    }



    @Override
    public void upload()
    {

    }

    @Override
    public void download()
    {

    }

    @Override
    public void delete()
    {

    }
}
