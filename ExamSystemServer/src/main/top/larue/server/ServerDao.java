package top.larue.server;

import top.larue.model.User;

/**
 * 编程实现数据的存取（模拟数据库）
 */
public class ServerDao {

    /**
     * 编程实现管理员账号和密码的校验并将结果返回回去
     */
    public boolean serverManagerCheck(User user) {
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * 编程实现学员账号和密码的校验并将结果返回回去
     */
    public boolean serverUserCheck(User user) {
        if ("user".equals(user.getUsername()) && "user".equals(user.getPassword())) {
            return true;
        }
        return false;
    }
}
