package top.larue.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInitAndClose {
    /** 服务端ServerSocket对象 */
    private ServerSocket ss;

    /** 服务端Socket对象 */
    private Socket s;

    public ServerSocket getSs() {
        return ss;
    }

    public void setSs(ServerSocket ss) {
        this.ss = ss;
    }

    /**
     * 服务器的初始化操作
     */
    public void serverInit() throws IOException {
        // 1. 创建ServerSocket对象并提供端口号
        ss = new ServerSocket(8888);
        // 2. 等待客户端的连接请求，调用accept方法
        System.out.println("服务器已经启动，等待客户端连接");
        while (true) {
            s = ss.accept();
            ServerStreamRunnable ssr = new ServerStreamRunnable(s);
            // 3. 启动新线程处理每一个客户端连接
            new Thread(ssr).start();
        }
    }

    /**
     * 服务端的关闭
     */
    public void serverClose() throws IOException {
        // 4. 关闭Socket并释放资源
        ss.close();
        System.out.println("服务器已经关闭");
    }
}
