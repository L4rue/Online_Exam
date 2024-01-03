package top.larue.model;

import java.io.Serial;
import java.io.Serializable;

public class UserMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = -1002698341657686069L;
    /** 消息类型，用于业务区分 */
    private String type;
    /** 消息内容 */
    private User user;

    public UserMessage(String type, User user) {
        this.type = type;
        this.user = user;
    }

    public UserMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "type='" + type + '\'' +
                ", user=" + user +
                '}';
    }
}
