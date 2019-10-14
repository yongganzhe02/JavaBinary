import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

//客户电脑端，测试用，已废除，改为Connect类
public class Computer {
    public static void main(String[] args) {

        //开始查看数据
        try {
            Socket socket2 = new Socket("127.0.0.1", 9001);
            System.out.println("Computer已就绪，开始连接服务器...");

            //Computer读
            InputStream input = socket2.getInputStream();
            byte data1[] = new byte[(200 * 16) + 1];
            input.read(data1);

            int data2[] = new int[(200 * 16) + 1];
            for (int i = 0; i < data1.length; i++) {
                data2[i] = data1[i] & 0xff;
                System.out.print(data2[i]+" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
