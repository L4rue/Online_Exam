package top.larue.server;

import java.io.IOException;

public class ServerTest {
    public static void main(String[] args) {
        // 1. 声明ServerInitAndClose对象
        ServerInitAndClose sic = new ServerInitAndClose();
        try {
            // 2. 初始化服务器
            sic.serverInit();
            // 3. 启动服务器
            ServerDao sd = new ServerDao();
            // 4. 接收客户端发来的消息
            // 5. 处理客户端发来的消息
            ServerView sv = new ServerView(sic, sd);
            sv.serverMainPage();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 3. 关闭服务器
                sic.serverClose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
