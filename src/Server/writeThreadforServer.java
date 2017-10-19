package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


public class writeThreadforServer implements Runnable{
    Socket S;
    ObjectOutputStream ous;
    Thread t;
    String filename;
    Hashtable<String, Inf>array;
    //ArrayList<Inf>array;
    public writeThreadforServer(Socket S, String filename, Hashtable<String, Inf> array){
        this.S = S;
        this.filename = filename;
        this.array = array;
        t = new Thread(this);
        t.start();
    }

    public void run(){
        try{
            ObjectOutputStream o = new ObjectOutputStream(S.getOutputStream());
            o.writeObject(array);
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
