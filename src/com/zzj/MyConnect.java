package com.zzj;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
                byte data[] = new byte[(200 * 16) + 1];
                input.read(data);

//            //打印
//            for (int i = 0; i < data1.length; i++) {
//                System.out.print(data1[i] + " ");
//            }

//进行解析：
//            Map tagsegment = {"Type": 0, "Tag ID": 0, "RSSI": 0, "Trigger ID": 0, "RSSI125K": 0, "Key": 0, "Accelerator": 0,
//                    "Activate": 0, "Apart": 0, "Voltage": 0, "Time": 0}

                int temp;
                StringBuilder fin = new StringBuilder();
                Map<String, String> tagsegment = new HashMap<String, String>();


                for (int i = 0; i < data[0]; i++) {
                    temp = data[1 + 16 * i];
                    temp = temp * 256 + data[2 + 16 * i];
                    temp = temp * 256 + data[3 + 16 * i];
                    temp = temp * 256 + data[4 + 16 * i];
                    tagsegment.put("Tag ID", String.valueOf(temp));
                    tagsegment.put("RSSI", String.valueOf(data[5 + 16 * i]));

                    if ((data[9 + 16 * i] & 0x01) == 0) {
                        tagsegment.put("Voltage", "OK");
                    } else {
                        tagsegment.put("Voltage", "Fault");
                    }

                    String st = tagsegment.get("Tag ID") + ": RSSI:" + tagsegment.get("RSSI") + " Voltage:" + tagsegment.get("Voltage");
                    fin.append(st);
                }


                //转字符串
                System.out.println(fin);
                System.out.println();


            }else if (msg.equals("bye")) {
                break;
            }else{
                System.out.println("命令错误");
            }
        }
    }

}