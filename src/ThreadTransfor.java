import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ThreadTransfor extends Thread {
    private InputStream input;

    public ThreadTransfor(InputStream input) {
        super();
        this.input = input;
    }

    @Override
    public void run() {
        byte[] y = new byte[(200 * 16) + 1 + 11];
        int[] z = new int[y.length];

        int data[] = new int[(200 * 16) + 1];

        while (true) {
            try {
                input.read(y);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < z.length; i++) {
                z[i] = y[i] & 0xff;

            }

            //判断头
            if (z[0] == 0xaa && z[1] == 0x55) {
                DataContainer.length = (z[2] << 8) + z[3];
                int uSum = 0;

                //校验和
                for (int i = 0; i < DataContainer.length + 2 - 1; i++) {
                    uSum = uSum + z[i];
                }
                uSum = ((~uSum) + 1) & 0xff;

                if (uSum == z[DataContainer.length + 2 - 1]) {//校验成功

                    DataContainer.deviceID = (z[4] << 24) + (z[5] << 16) + (z[6] << 8) + (z[7]);
                    DataContainer.cmd = z[8];
                    DataContainer.sn = z[9];
                    for (int i = 10; i < DataContainer.length - 9; i++) {
                        data[i - 10] = z[i];
                    }

//                    for (int i = 0; i < data.length; i++) {
//                        DataContainer.data1[i] = (byte) (data[i]);
//
//                    }

                    //解析存储
                    int temp;
                    Map<String, String> tagsegment = new HashMap<String, String>();

                    for (int i = 0; i < data[0]; i++) {
                        temp = data[2 + 16 * i];
                        temp = temp * 256 + data[3 + 16 * i];
                        temp = temp * 256 + data[4 + 16 * i];
                        temp = temp * 256 + data[5 + 16 * i];
                        tagsegment.put("Tag ID", String.valueOf(temp));
                        tagsegment.put("RSSI", String.valueOf(data[6 + 16 * i]));

                        if ((data[10 + 16 * i] & 0x01) == 0) {
                            tagsegment.put("Voltage", "OK");
                        } else {
                            tagsegment.put("Voltage", "Fault");
                        }


                        String st = tagsegment.get("Tag ID") + ": RSSI:" + tagsegment.get("RSSI") + " Voltage:" + tagsegment.get("Voltage");
                        long t1 = System.currentTimeMillis();
                        List list =new ArrayList();
                        list.add(st);
                        list.add(t1);

                        String name=tagsegment.get("Tag ID").substring(0,10);
                        DataContainer.buffer.put(name,list);

                    }

                    System.out.println("数据已存储！");


                } else {
                    System.out.println("校验和错误！");

                }

            } else {
                System.out.println("头部错误！");
                continue;
            }
        }
    }
}
