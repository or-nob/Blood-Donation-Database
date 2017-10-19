package Server; /**
 * Created by user on 12/20/2015.
 */
import hu.ozeki.OzSMSMessage;

import java.io.*;
import java.util.Scanner;

public class TestTcpSms implements  Runnable{

    /**
     * @param args
     */
    Thread t;
    String host;
    int port;
    String username;
    String password;
    public TestTcpSms(String host, int port, String username, String password){
        this.host = host;
        this.password = password;
        this.username = username;
        this.port = port;
        this.t = new Thread(this);
        t.start();
    }
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            /**
             * Connect to Ozeki NG SMS Gateway and logging in.
             */
                MyOzSmsClient osc = new MyOzSmsClient(host, port);
                osc.login(username, password);
                System.out.println("SMS message:");

                /**
                 * If logged in send "Text message" to number "+00112233"
                 */
                //sc.nextLine();
                OzSMSMessage osx = new OzSMSMessage();
                osc.doOnMessageReceived(osx);
                //osc.logout();
            /**
             * Receiving message:
             *
             * If you want to receive messages you can use doOnMessageReceived in MyOzSmsClient.java
             * That's an event, which runs automatically when a message is received.
             */

        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        TestTcpSms obj = new TestTcpSms("localhost", 9500, "admin", "arnob");
    }
}

