package sftp;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.RemoteResourceInfo;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.IOException;
import java.util.List;

public class SSHJTest
{
    public static void main(String[] args)
    {
        try
        {
            SSHClient ssh = new SSHClient();
//            ssh.loadKnownHosts();
            // You may set the SSH client to accept all keys without any verification (ignores host key verification)
            // https://www.e-learn.cn/topic/3034569
            ssh.addHostKeyVerifier(new PromiscuousVerifier());
            // 先连接再鉴权
            ssh.connect("222.73.39.37", 50022);

            String keyPath = "/Users/zhanggaoqiang/code/demo/liangzizhifu/java_demo_zhanghu/src/com/sina/tools/id_rsa";
            ssh.authPublickey("200009166771", keyPath);

            SFTPClient sftp = ssh.newSFTPClient();
            List<RemoteResourceInfo> infos = sftp.ls("/upload");
            for (RemoteResourceInfo info : infos)
            {
                System.out.println(info.getName());
            }

            sftp.close();
            ssh.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
