package top.larue.server;

import top.larue.model.UserMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 实现服务器主功能
 */
public class ServerView {

    /**
     * 合成复用原则
     */
    private final ObjectInputStream ois;

    private final ObjectOutputStream oos;

    private final ServerDao sd;

    public ServerView(ObjectInputStream ois, ObjectOutputStream oos, ServerDao sd) {
        this.ois = ois;
        this.oos = oos;
        this.sd = sd;
    }

    /**
     * 客户端发来消息的接收并处理
     *
     * @return true：客户端断开连接，false：客户端继续连接
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean serverMainPage() throws IOException, ClassNotFoundException {
        UserMessage userMessage = (UserMessage) ois.readObject();
        if (userMessage == null) return true;
        if ("quit".equals(userMessage.getType())) {
            System.out.println("客户端断开连接");
            return true;
        }
        System.out.println("客户端传来的信息是：" + userMessage);
        serverCheck(userMessage);
        return false;
    }

    private void serverCheck(UserMessage userMessage) throws IOException {
        if ("managerLogin".equals(userMessage.getType())) {
            if (sd.serverManagerCheck(userMessage.getUser())) {
                userMessage.setType("Success");
                oos.writeObject(userMessage);
            } else {
                userMessage.setType("Failed");
                oos.writeObject(userMessage);
            }
        } else {
            if (sd.serverUserCheck(userMessage.getUser())) {
                userMessage.setType("Success");
                oos.writeObject(userMessage);
            } else {
                userMessage.setType("Failed");
                oos.writeObject(userMessage);
            }
        }
    }
}
