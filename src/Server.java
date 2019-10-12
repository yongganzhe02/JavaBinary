import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //开启服务
            ServerSocket s = new ServerSocket(9000);
            System.out.println("服务已经开启,等待客户端访问");

            // 等待客户来访问（如果没有客户访问，该方法阻塞）
            Socket socket = s.accept();
            System.out.println("客户端" + socket.getInetAddress().getHostAddress() + "已连接");

            byte x[] = {(byte)0xaa,0x55,   0x11,0x22,   0x33,0x44,   0x55,   0x66,  0x77,(byte)0x88,   (byte)0x99};

            //服务器写
            OutputStream output = socket.getOutputStream();
            output.write(x);






            socket.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
