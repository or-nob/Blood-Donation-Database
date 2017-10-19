package sample;

import Server.Inf;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;


public class readThread implements  Runnable{
    Socket S;
    public String fullString;
    Thread T;
    Hashtable<String,Inf>msg;
    DonorListController controller;
    List<TableInf> tableInfs=new ArrayList<>();
    TableInf A;
    public readThread(Socket S,DonorListController controller, TableInf A){
        this.controller=controller;
        this.A = A;
        tableInfs.add(A);
        this.S = S;
        T = new Thread(this);
        T.start();
    }
    public readThread(Socket S, DonorListController controller){
        this.controller = controller;
        this.S = S;
        T = new Thread(this);
        T.start();
    }
    public readThread(Socket S){
        this.S = S;
        T = new Thread(this);
        T.start();
    }
    public void run(){
        while(true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(S.getInputStream());
                msg = (Hashtable<String,Inf>) ois.readObject();
                Enumeration e = msg.keys();
                while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();


                    System.out.println(key + " " + msg.get(key).getPhone()+" " + msg.get(key).getEmail()+" "+msg.get(key).getUserStatus());
                    TableInf tableInf=new TableInf(key,msg.get(key).getEmail(),msg.get(key).getPhone(),msg.get(key).getPassword(),msg.get(key).getBloodGr(),msg.get(key).getLastDonate(), msg.get(key).getUserStatus());
                    tableInfs.add(tableInf);
                    fullString = fullString+msg.get(key).getEmail();
                }
            } catch (Exception e) {
            }
            for (TableInf  inf:tableInfs){


            }
            try{
                controller.setList(tableInfs);
            }catch (Exception e){}

        }
    }
}

