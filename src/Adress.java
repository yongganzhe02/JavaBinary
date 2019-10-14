import java.net.InetAddress;
import java.net.UnknownHostException;

public class Adress  {
    public static void main(String[] args) throws UnknownHostException {

        InetAddress address1 = InetAddress.getByName("www.163.com");
        InetAddress address2 = InetAddress.getByName("210.26.116.38");
        InetAddress address3 = InetAddress.getByName(null);//本机
        //上面两种方法将字符串转换为地址类型

        System.out.println(address1.getHostAddress());
        System.out.println(address2.getHostAddress());
        System.out.println(address3.getHostAddress());
        //获取IP地址






    }
}
