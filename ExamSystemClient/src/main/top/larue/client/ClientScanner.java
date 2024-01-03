package top.larue.client;

import java.util.Scanner;

/**
 * 扫描器封装
 */
public class ClientScanner {
    private static Scanner sc = new Scanner(System.in);

    /**
     * 获取扫描器
     */
    public static Scanner getScanner() {
        return sc;
    }

    /**
     * 关闭
     */
    public static void close() {
        sc.close();
    }
}
