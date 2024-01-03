package top.larue.server;

import top.larue.model.UserMessage;

import java.io.IOException;

/**
 * 实现服务器主功能
 */
public class ServerView {

    /**
     * 合成复用原则
     */
    private final ServerInitAndClose sic;

    private final ServerDao sd;

    public ServerView(ServerInitAndClose sic, ServerDao sd) {
        this.sic = sic;
        this.sd = sd;
    }

    /**
     * 客户端发来消息的接收并处理
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void serverMainPage() throws IOException, ClassNotFoundException {
        UserMessage userMessage = (UserMessage) sic.getOis().readObject();
        System.out.println("客户端传来的信息是：" + userMessage.toString());
        if (sd.serverManagerCheck(userMessage.getUser())) {
            userMessage.setType("Success");
            sic.getOos().writeObject(userMessage);
        } else {
            userMessage.setType("Failed");
            sic.getOos().writeObject(userMessage);
        }
    }
}
