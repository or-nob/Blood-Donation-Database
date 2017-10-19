package Server;

/**
 * Created by user on 12/20/2015.
 */


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import hu.ozeki.*;
import sample.DateandTime;

public class MyOzSmsClient extends OzSmsClient{
    public String s;
    public String msg1;
    public MyOzSmsClient(String host, int port) throws IOException, InterruptedException {
        super(host, port);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void doOnMessageAcceptedForDelivery(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message accepted for delivery. ID: " + sms.messageId);
    }

    @Override
    public void doOnMessageDeliveredToHandset(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message delivered to handset. ID: " + sms.messageId);
    }

    @Override
    public void doOnMessageDeliveredToNetwork(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message delivered to network. ID: " + sms.messageId);
    }

    @Override
    public void doOnMessageDeliveryError(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message could not be delivered. ID: " + sms.messageId + " Error message: " + sms.errorMessage + "\r\n");
    }

    @Override
    public void doOnMessageReceived(OzSMSMessage sms) {
        Date now = new Date();
        System.out.println(now.toString() + " Message received. Sender address: " + sms.sender + " Message text: " + sms.messageData  + " at: "+ sms.receivedDate);
        s = sms.sender;
        //message format will be: Blood Group+" "+Area;
        msg1 = sms.messageData;
        String[] tokens1 = msg1.split(" ");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String str = dateFormat.format(date);
        String[] tokens2 = str.split(" ");
        String TDate = tokens2[0];
        //System.out.println(s);
        try{
            FileInputStream fstream = new FileInputStream("DataBase.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine ;
            String message = "";
            while ((strLine = br.readLine()) != null)   {
                String[] tokens = strLine.split(" ");
                DateandTime obj = new DateandTime(TDate, tokens[5]);
                if(tokens1[0].equals(tokens[4])&&obj.diff()){
                    message = message + tokens[0] + " " + tokens[1] + " " + tokens[2] + " ";
                }
            }
            //System.out.println(message);
            sendMessage(s, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doOnClientConnectionError(int errorCode, String errorMessage) {
        Date now = new Date();
        System.out.println(now.toString() + " Message code: " + errorCode + ", Message: " + errorMessage);
    }
   // public String gets(){return s;}
}

