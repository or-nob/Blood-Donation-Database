package Server;
import sun.misc.IOUtils;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;
import java.lang.*;
import java.util.*;


import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class RAWF {
    String filename;
    String Name;
    String Email;
    String Phone;
    String Content;
    String Password;
    String bloodGr;
    String lastDonate;
    String getS;
    RAWF(String filename, String Name, String Email, String Phone, String Password,String bloodGr,String lastDonate,String getS) {
        this.filename = filename;
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
        this.Password = Password;
        this.bloodGr = bloodGr;
        this.lastDonate = lastDonate;
        this.getS = getS;
    }
    RAWF(String filename){
        this.filename = filename;
    }
    void append(){
        try
        {
            Content = Name+" "+Email+" " +Phone+" "+Password+" "+bloodGr+" "+lastDonate+" "+getS+"\r\n";
            FileWriter fw = new FileWriter(filename,true);
            fw.write(Content);
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    void read(){

    }
}
