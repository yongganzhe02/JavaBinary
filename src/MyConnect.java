import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class MyConnect {
    public static void main(String[] args) throws Exception {

        String host = "fd00:1:2:3::1";
        //int port = 16800;
        //String host = "127.0.0.1";
        int port = 9001;
        String msg = null;
        Scanner s = new Scanner(System.in);

        while (true) {

            System.out.print("input < ");
            msg = s.next();

            if (msg.equals("find")) {
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
                byte data1[] = new byte[1024];
                input.read(data1);

                String show = new String(data1);
                System.out.println(show);


            } else if (msg.equals("bye")) {
                break;
            } else {
                System.out.println("命令错误");
            }


        }
    }
}
