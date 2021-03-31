package sftp;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.IOException;

public class SSHJTest
{
    public static void main(String[] args)
    {
        try
        {
            SSHClient ssh = new SSHClient();
//            ssh.loadKnownHosts();
            ssh.addHostKeyVerifier(new PromiscuousVerifier());
            // 先连接再鉴权
            ssh.connect("", 22);

            String keyPath = "";
            ssh.authPublickey("", keyPath);

            SFTPClient sftp = ssh.newSFTPClient();
            System.out.println(sftp.ls(""));;
            sftp.close();
            ssh.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
