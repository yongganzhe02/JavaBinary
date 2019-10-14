import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//检测校验服务器
public class Server {
    public static void main(String[] args) throws Exception {

        //开启服务
        ServerSocket s = new ServerSocket(9000);
        System.out.println("服务已经开启,等待RFID访问");

        // 等待RFID来访问（如果没有RFID访问，该方法阻塞）
        Socket socket = s.accept();
        System.out.println("RFID " + socket.getInetAddress().getHostAddress() + "已连接");


        //服务器读
        InputStream input = socket.getInputStream();
        new ThreadTransfor(input).start();

        ServerSocket s1 = new ServerSocket(9001);
        System.out.println("服务已经开启,等待客户端访问");

        //服务器接收指令
        new ThreadControl(s1).start();

    }

}
