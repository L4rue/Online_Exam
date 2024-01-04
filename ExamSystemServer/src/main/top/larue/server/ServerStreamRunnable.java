package top.larue.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStreamRunnable implements Runnable {
    /** 服务端Socket对象 */
    private Socket s;

    /** 服务端输入流 */
    private ObjectInputStream ois;

    /** 服务端输出流 */
    private ObjectOutputStream oos;

    public ServerStreamRunnable(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            // 1. 使用输入输出流，与客户端通信
            ois = new ObjectInputStream(s.getInputStream());
            oos = new ObjectOutputStream(s.getOutputStream());
            System.out.println("新客户端连接，启动线程："+ Thread.currentThread().getName());
            // 2. 启动数据库
            ServerDao sd = new ServerDao();
            // 3. 接收客户端发来的消息
            ServerView sv = new ServerView(ois, oos, sd);
            while (true) {
                if(sv.serverMainPage()) break;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ois.close();
                oos.close();
                s.close();
                System.out.println("客户端断开连接，释放线程："+ Thread.currentThread().getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
