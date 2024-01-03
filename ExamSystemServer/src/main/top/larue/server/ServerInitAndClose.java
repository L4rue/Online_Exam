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

    /** 服务端输入流 */
    private ObjectInputStream ois;

    /** 服务端输出流 */
    private ObjectOutputStream oos;

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    /**
     * 服务器的初始化操作
     */
    public void serverInit() throws IOException {
        // 1. 创建ServerSocket对象并提供端口号
        ss = new ServerSocket(8888);
        // 2. 等待客户端的连接请求，调用accept方法
        System.out.println("服务器已经启动，等待客户端连接");
        s = ss.accept();
        System.out.println("客户端已经连接");
        // 3. 使用输入输出流，与客户端通信
        ois = new ObjectInputStream(s.getInputStream());
        oos = new ObjectOutputStream(s.getOutputStream());
        System.out.println("服务器初始化成功");
    }

    /**
     * 服务端的关闭
     */
    public void serverClose() throws IOException {
        // 4. 关闭Socket并释放资源
        oos.close();
        ois.close();
        s.close();
        ss.close();
        System.out.println("服务器已经关闭");
    }
}
