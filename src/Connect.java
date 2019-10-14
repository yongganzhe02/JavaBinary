import com.alibaba.fastjson.JSONArray;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Connect {
    public static void main(String[] args) throws Exception {

        //String host = "fd00:1:2:3::1";
        //int port = 16800;
        String host = "127.0.0.1";
        int port = 9001;
        boolean auto_send_flag = false;
        String msg = "find";


//        while (true) {
//            if (auto_send_flag == true) {
//                msg = "find";
//                Thread.sleep(500);
//            } else {
//                Scanner s=new Scanner(System.in);
//                System.out.print("input < ");
//                msg=s.next();
//                if (msg == "bye") break;
//            }
//
//        }

        Socket socket = new Socket(InetAddress.getByName(host), port);

        // 流写到服务器端 --->服务器端进行读操作
        //控制台写
        //PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
        //pw.println(msg.getBytes());

        //控制台读
        //BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //String str1 = br.readLine();

        //流直接写
        OutputStream output = socket.getOutputStream();
        output.write(msg.getBytes());

        //流直接读
        InputStream input = socket.getInputStream();
        byte data1[] = new byte[(200 * 16) + 1];
        input.read(data1);

        //直接读
        for (int i = 0; i < data1.length; i++){
        System.out.print(data1[i]+" ");}

        //转字符串
//        String str= new String (data1);
//        System.out.println(str);
    }
}

