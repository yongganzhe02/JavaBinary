package com.zj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//测试流用，已废弃
public class Server1 {
    public static void main(String[] args) {
        try {
            //开启服务
            ServerSocket s = new ServerSocket(9000);
            System.out.println("服务已经开启,等待RFID访问");

            // 等待RFID来访问（如果没有RFID访问，该方法阻塞）
            Socket socket = s.accept();
            System.out.println("" + socket.getInetAddress().getHostAddress() + "已连接");


            //服务器读
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str1 = br.readLine();
            System.out.println(str1);

            //从服务器端键盘读一行数据发给客户端

            String str = "abcdefg";
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
