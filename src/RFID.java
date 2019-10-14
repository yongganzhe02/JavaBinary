import java.io.*;
import java.net.Socket;

//测试用，数据源，正式不用，改为连接工控机
public class RFID {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 9000);
        System.out.println("RFID已就绪，开始连接服务器...");

        //RFID写
        OutputStream output = socket.getOutputStream();
        while (true) {
            byte x[] = {(byte) 0xaa, 0x55, 0x00, 0x0e, 0x00, 0x00, 0x00, 0x01, (byte) 0xFF, 0x01, 0x04, 0x00, (byte) 0xff, (byte) 0xef};
            output.write(x);
            Thread.sleep(1000);
        }


    }

}
