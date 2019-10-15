import java.util.Set;

public class ThreadAlert extends Thread {
    @Override
    public void run() {

        while (true) {
            System.out.println("检测中...");
            Set set = DataContainer.buffer.keySet();
            for (Object object : set) {
                String key = (String) object;
                long t1 = (long) (DataContainer.buffer.get(key)).get(1);
                long t2 = System.currentTimeMillis();
                if (t2 - t1 > 1000) {
                    System.out.println(object + "丢失时间过长!");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
