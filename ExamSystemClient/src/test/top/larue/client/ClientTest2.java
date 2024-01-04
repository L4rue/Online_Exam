package top.larue.client;

import java.io.IOException;

public class ClientTest2 {

    public static void main(String[] args) {
        // 1. 声明ClientInitAndClose对象
        ClientInitAndClose cic = null;

        try {
            cic = new ClientInitAndClose();
            // 2. 初始化客户端
            cic.clientInit();
            ClientView cv = new ClientView(cic);
            cv.clientMainPage();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 3. 关闭客户端
                cic.clientClose();
                ClientScanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
