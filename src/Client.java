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


            int count=0;
            for(int i=y.length-1;i>=0;i--)
            {
                if(y[i]==0)
                {count++;}
                else {
                    break;
                }
            }

            String[] s=new String[y.length-count];
            for(int i=0;i<s.length;i++) {
                String hexString = Integer.toHexString(y[i]);
                if(hexString.length()>=2)
                    hexString=hexString.substring(hexString.length()-2);
                s[i]=hexString;
            }

            for(String ss:s){
                System.out.println(ss);
            }





            input.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
