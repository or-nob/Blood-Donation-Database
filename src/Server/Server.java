package Server;

//import sample.readThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;


class communicate implements Runnable{
    ServerSocket sssock;
    public Hashtable<String, Inf> array = new Hashtable<String, Inf>();
    //public ArrayList<Inf> array= new ArrayList <Inf>();
    Thread t;
    communicate(){
        t = new Thread(this);
        t.start();
    }
    public void run(){
        try{
            sssock = new ServerSocket(8080);
            TestTcpSms obj = new TestTcpSms("localhost", 9500, "admin", "arnob");
            Socket S;
            while(true) {
                S = sssock.accept();
                //System.out.println("Connected to" + S.getInetAddress());
                readThreadforServer T = new readThreadforServer(S);
                FileInputStream fstream = new FileInputStream("DataBase.txt");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null)   {
                        String[] tokens = strLine.split(" ");
                        try {
                            System.out.println(tokens[4]);
                            Inf Obj = new Inf(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                            array.put(tokens[0], Obj);
                        }catch (Exception e){}
                }
                System.out.println(array.size());
                writeThreadforServer W = new writeThreadforServer(S, "DataBase.txt", array);
            }
        }catch(IOException e){}
    }
}

public class Server{
    public static void main(String[] args) {
        communicate t = new communicate();
    }
}