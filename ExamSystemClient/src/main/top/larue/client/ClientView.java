package top.larue.client;

import top.larue.model.User;
import top.larue.model.UserMessage;

import java.io.IOException;

/**
 * 实现客户端的主界面绘制和相应功能的实现
 */
public class ClientView {
    /**
     * 为了使用输入输出流，采用合成复用原则实现
     */
    private final ClientInitAndClose cic;

    public ClientView(ClientInitAndClose cic) {
        this.cic = cic;
    }

    /**
     * 绘制主界面
     */
    public void clientMainPage() throws IOException {

        System.out.println("\n==========================");
        System.out.println("==\t欢迎进入在线考试系统\t==");
        System.out.println("==========================");
        System.out.println("==\t [1] 学员登陆\t\t==");
        System.out.println("==\t [2] 管理员登陆\t\t==");
        System.out.println("==\t [0] 退出\t\t\t==");
        System.out.println("==========================");
        System.out.println("请选择:");
        while (true) {
            int choice = ClientScanner.getScanner().nextInt();
            switch (choice) {
                case 1:
                    System.out.println("正在进入学员系统...");
                    break;
                case 2:
                    clientManagerLogin();
                    System.out.println("正在进入管理员系统...");
                    break;
                case 0:
                    System.out.println("退出");
                    return;
                default:
                    System.out.println("输入有误，请重新输入...");
                    break;
            }
        }
    }

    /**
     * 客户端管理员登陆
     */
    private void clientManagerLogin() throws IOException {
        // 获取用户输入
        System.out.println("请输入管理员的账户信息：");
        String username = ClientScanner.getScanner().next();
        System.out.println("请输入密码：");
        String password = ClientScanner.getScanner().next();
        // 准备管理员信息
        UserMessage userMessage = new UserMessage("managerLogin", new User(username, password));
        // 将UserMessage类型的数据发送给服务器
        cic.getOos().writeObject(userMessage);
    }
}
