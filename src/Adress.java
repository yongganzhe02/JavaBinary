import java.net.InetAddress;
import java.net.UnknownHostException;

public class Adress {
    public static void main(String[] args) {

        InetAddress address = null;
        try {
            address = InetAddress.getByName("www.163.com");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(address.getHostAddress());
        InetAddress address1 = null;
        try {
            address1 = InetAddress.getByName(null);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(address1.getHostAddress());


    }
}
