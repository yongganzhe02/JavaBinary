import java.io.*;
import java.net.Socket;
import java.util.Arrays;


public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            System.out.println("客户端连接服务器");

            //客户端读
            InputStream input = socket.getInputStream();
            byte[] y=new byte[(200*16)+1+11];
            input.read(y);


            int length;
            int deviceID;
            int cmd;
            int sn;
            int data[]=new int[(200*16)+1];
            int[] z=new int[y.length];
            for(int i=0;i<z.length;i++) {
                z[i]=y[i]&0xff;
            }

            //判断头
            if(z[0]==0xaa&&z[1]==0x55)
            {
                length=(z[2]<<8)+z[3];
                 int uSum=0;

                //校验：
                boolean b=false;
                for(int i=0;i<length-1;i++)
                {
                    uSum=uSum+z[i];
                }

                uSum=((~uSum)+1)&0xff;

                if(uSum==z[length-1])
                {


                    deviceID = (z[4] << 24) + (z[5] << 16) + (z[6] << 8) + (z[7]);
                    cmd = z[8];
                    sn = z[9];
                    for (int i = 10; i < length - 1; i++) {
                        data[i - 10] = z[i];
                    }
                    System.out.println("success!");
                }

            }







            input.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
