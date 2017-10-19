package sample;

import Server.Inf;
import Server.InfString;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;


public class writeThread{
    Socket S;
    public writeThread(Socket S){
        this.S = S;
    }
    public void write(InfString string){
        try {
            ObjectOutputStream o = new ObjectOutputStream(S.getOutputStream());
            o.writeObject(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String  string){
        try {
            ObjectOutputStream o = new ObjectOutputStream(S.getOutputStream());
            o.writeObject(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
