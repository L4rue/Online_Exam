package top.larue.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientInitAndClose {
    /** 客户端Socket对象 */
    private Socket s;

    /** 客户端输出流 */
    private ObjectOutputStream oos;

    /** 客户端输入流 */
    private ObjectInputStream ois;

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    /**
     * 客户端初始化
     */
    public void clientInit() throws IOException {
        // 1. 创建Socket对象并指定服务器的通信地址
        s = new Socket(InetAddress.getLocalHost(), 8888);

        // 2. 使用输入输出流与服务器交流
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        System.out.println("客户端连接成功");
    }

    /**
     * 客户端关闭
     */
    public void clientClose() throws IOException {
        // 1. 关闭输入输出流
        oos.close();
        ois.close();
        // 2. 关闭Socket
        s.close();
        System.out.println("客户端已关闭");
    }
}
