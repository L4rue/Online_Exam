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
    public void clientMainPage() throws IOException, ClassNotFoundException {

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
                    // 学员登陆
                    clientUserLogin();
                    break;
                case 2:
                    // 管理员登陆
                    clientManagerLogin();
                    break;
                case 0:
                    // 退出客户端
                    quieClient();
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
    private void clientManagerLogin() throws IOException, ClassNotFoundException {
        // 获取用户输入
        System.out.print("请输入管理员的账户信息：");
        String username = ClientScanner.getScanner().next();
        System.out.print("请输入密码：");
        String password = ClientScanner.getScanner().next();
        // 准备学员信息
        UserMessage userMessage = new UserMessage("managerLogin", new User(username, password));
        // 将UserMessage类型的数据发送给服务器
        cic.getOos().writeObject(userMessage);
        UserMessage tum = (UserMessage) cic.getOis().readObject();
        if ("Success".equals(tum.getType())) {
            System.out.println("管理员登陆成功");
        } else {
            System.out.println("管理员登陆失败");
        }
    }

    /**
     * 学员登陆
     */
    private void clientUserLogin() throws IOException, ClassNotFoundException {
        // 获取用户输入
        System.out.print("请输入学员的账户信息：");
        String username = ClientScanner.getScanner().next();
        System.out.print("请输入密码：");
        String password = ClientScanner.getScanner().next();
        // 准备管理员信息
        UserMessage userMessage = new UserMessage("userLogin", new User(username, password));
        // 将UserMessage类型的数据发送给服务器
        cic.getOos().writeObject(userMessage);
        UserMessage tum = (UserMessage) cic.getOis().readObject();
        if ("Success".equals(tum.getType())) {
            System.out.println("用户登陆成功");
        } else {
            System.out.println("用户登陆失败");
        }
    }

    /**
     * 客户端退出
     */
    private void quieClient() throws IOException {
        cic.getOos().writeObject(new UserMessage("quit",null));
    }
}
