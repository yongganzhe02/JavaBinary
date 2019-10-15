package com.zj;

import java.net.ServerSocket;
import java.net.Socket;


public class Connect1 {
    public static void main(String[] args) throws Exception {

        //String host = "fd00:1:2:3::38";
        String host = "210.26.116.138";
        //String host = "fd00:1:2:3::1";
        //int port = 16800;

        //String host = "127.0.0.1";
        //int port = 9000;

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
		
		ServerSocket s = new ServerSocket(16800);
		Socket socket = s.accept();
		System.out.println("ok");

        
        /*PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
        pw.println(msg);*/



        
        /*BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String str1 = br.readLine();
        System.out.println(str1);
        socket.close();*/



        }
    }

