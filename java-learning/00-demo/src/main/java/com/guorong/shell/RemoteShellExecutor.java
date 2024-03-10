package com.guorong.shell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.junit.jupiter.api.Test;

public class RemoteShellExecutor {
    private static final String USER_NAME = "appdeploy";
    private static final String PASSWD = "BrysjHhrhl!@12";
    private static final String HOST = "10.203.15.80";

    private static final int PORT = 22;

    @Test
    public void test() {
        RemoteShellExecutor robot = new RemoteShellExecutor();
        robot.executeShellFile("/app/demo.sh", null);
    }

    private int executeShellFile(String scriptFileName, List<String> paramList) {
        if (Objects.isNull(scriptFileName) || scriptFileName.length() == 0) {
            throw new IllegalArgumentException("scriptFileName is not empty");
        }
        try {
            // ssh 连接
            JSch jsch = new JSch();
            Session session = jsch.getSession(USER_NAME, HOST, PORT);
            // StrictHostKeyChecking(严格的主机密钥检查)
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(PASSWD);
            session.connect();
            // 在会话上创建执行通道
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            // 获取此通道的输入流。从远程端作为消息传入的所有数据都可以从此流中读取。
            InputStream in = channelExec.getInputStream();
            // 设置要执行的命令(此处它是远程 shell 脚本)
            StringBuilder commandSb = new StringBuilder("sh")
                    .append(" ").append(scriptFileName);
            if (Objects.nonNull(paramList) && paramList.size() > 0) {
                String paramStr = paramList
                        .stream()
                        .collect(Collectors.joining(" "));
                commandSb.append(" ").append(paramStr);
            }
            channelExec.setCommand(commandSb.toString());
            // 执行命令
            channelExec.connect();
            // 读取我们上面设置的输入流的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            // 从缓冲读取器读取每一行并将其添加到结果列表中,您也可以在此处简单地打印结果
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("clearing -> " + line);
            }
            // 检索此通道对应的远程命令的退出状态
            int exitStatus = channelExec.getExitStatus();
            // 安全地断开频道并断开会话。如果不这样做，则可能会导致资源泄漏
            channelExec.disconnect();
            session.disconnect();
            // 执行结果判断
            if (exitStatus < 0) {
                System.out.println("done, but exit status not set!");
            }
            else if (exitStatus > 0) {
                System.out.println("done, but with error!");
            }
            else {
                System.out.println("done!");
            }

        } catch (Exception e) {
            System.err.println("error: " + e);
        }
        return 0;
    }

}