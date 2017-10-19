package Server;

import java.io.*;
import java.net.Socket;
import java.util.Hashtable;

public class readThreadforServer implements  Runnable{
    Socket S;
    Thread T;
    RAWF fileAppending;
    InfString msg;
    Hashtable<String, Inf> array = new Hashtable<String, Inf>();
    public readThreadforServer(Socket S){
        this.S = S;
        T = new Thread(this);
        T.start();
    }
    public void run(){
        while(true) {
            try {
                ObjectInputStream in = new ObjectInputStream(S.getInputStream());
                msg = (InfString) in.readObject();
                if (msg.getS().equals("OnEdit")){
                    File f=new File("DataBase.txt");
                    System.out.println("OnEdit");
                    f.delete();
                    FileOutputStream fout=new FileOutputStream("DataBase.txt");
                    fout.flush();
                    fout.close();
                }
                if(msg.getS().equals("Submit")) {
                    System.out.println(msg.getObj().getBloodGr());
                    fileAppending = new RAWF("DataBase.txt", msg.getObj().getName(), msg.getObj().getEmail(), msg.getObj().getPhone(), msg.getObj().getPassword(), msg.getObj().getBloodGr(), msg.getObj().getLastDonate(), msg.getObj().getUserStatus());
                    fileAppending.append();
                }
                /*if(msg.getS().equals("Edit")){
                    try {
                        FileInputStream fstream = new FileInputStream("DataBase.txt");
                        DataInputStream m = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(m));\
                        String strLine = null;
                        while ((strLine = br.readLine()) != null)   {
                            String[] tokens = strLine.split(" ");
                            Inf Obj = new Inf(tokens[0], tokens[1], tokens[2]);
                            array1.put(tokens[0],Obj);
                        }
                    }catch(Exception e){}
                    for(Hasttable){}
                }*/

            } catch (Exception e) {
            }
        }
    }
}

