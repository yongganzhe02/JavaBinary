import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//检测校验服务器
public class Server {
    public static void main(String[] args) {
        try {
            //开启服务
            ServerSocket s = new ServerSocket(9000);
            System.out.println("服务已经开启,等待RFID访问");

            // 等待RFID来访问（如果没有RFID访问，该方法阻塞）
            Socket socket = s.accept();
            System.out.println("" + socket.getInetAddress().getHostAddress() + "已连接");

            //服务器读
            InputStream input = socket.getInputStream();
            byte[] y = new byte[(200 * 16) + 1 + 11];

            int length;
            int deviceID;
            int cmd;
            int sn;
            //byte data[] = new byte[(200 * 16) + 1];
            int data[] = new int[(200 * 16) + 1];
            int[] z = new int[y.length];

//            while (true) {
                input.read(y);
                for (int i = 0; i < z.length; i++) {
                    z[i] = y[i] & 0xff;

                }

                //判断头
                if (z[0] == 0xaa && z[1] == 0x55) {
                    length = (z[2] << 8) + z[3];
                    int uSum = 0;
                    //校验和
                    for (int i = 0; i < length - 1; i++) {
                        uSum = uSum + z[i];
                    }
                    uSum = ((~uSum) + 1) & 0xff;
                    if (uSum == z[length - 1]) {//校验成功

                        deviceID = (z[4] << 24) + (z[5] << 16) + (z[6] << 8) + (z[7]);
                        cmd = z[8];
                        sn = z[9];
                        for (int i = 10; i < length - 1; i++) {
                            data[i - 10] = z[i];
                            System.out.println(data[i-10]);
                        }
                        System.out.println("数据已存储！");



                        //开启服务
                        ServerSocket s1 = new ServerSocket(9001);
                        System.out.println("服务已经开启,等待客户端访问");

                        // 等待客户来访问（如果没有客户访问，该方法阻塞）
                        Socket socket1 = s1.accept();
                        System.out.println("客户端" + socket1.getInetAddress().getHostAddress() + "已连接");

//                        服务器写
                        OutputStream output1 = socket1.getOutputStream();
//                        while(true) {
                            byte data1[] = new byte[(200 * 16) + 1];

                            for (int i = 0; i < data.length; i++) {
                                data1[i] = (byte) data[i];
                            }
                            output1.write(data1);
//                            break;
//                        }

                    }else{
                        System.out.println("校验和错误！");
                    }

                }else{
                    System.out.println("头部错误！");
                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
