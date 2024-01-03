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

    public ServerView(ServerInitAndClose sic) {
        this.sic = sic;
    }

    public void serverMainPage() throws IOException, ClassNotFoundException {
        UserMessage userMessage = (UserMessage) sic.getOis().readObject();
        System.out.println("客户端传来的信息是：" + userMessage.toString());
    }
}
