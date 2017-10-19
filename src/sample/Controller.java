package sample;

import Server.Inf;
import Server.InfString;
import Server.TestTcpSms;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


public class Controller implements Runnable{
    //public String Namet;
    static String datestr;
    static String str;
    static String str1;
    static String List;
    static String userName;
    static boolean isUserAdmin;
    static TableInf B;
    static Inf A;
    static String ClientName;
    static String email;
    static String password;
    static String phone;
    public TextField searchTF;
    public Button searchButton;
    int cnt = 0;
    int cnt1 = 0;
    private String serverIP;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    @FXML
    private Button Notifications;
    @FXML
    private TextField IDName1;
    @FXML
    private TextField IDPhone1;
    @FXML
    private TextField IDEmail1;
    @FXML
    private PasswordField IDPassword1;
    @FXML
    private CheckBox Opos1;
    @FXML
    private CheckBox Oneg1;
    @FXML
    private CheckBox Apos1;
    @FXML
    private CheckBox Bpos1;
    @FXML
    private CheckBox ABpos1;
    @FXML
    private CheckBox Aneg1;
    @FXML
    private CheckBox Bneg1;
    @FXML
    private CheckBox ABneg1;
    @FXML
    private Button Submit1;
    @FXML
    private Button AddNewAdmin;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    private Button SignUp;
    @FXML
    private Button Reset;
    @FXML
    private Button LogIn;
    @FXML
    private Button Next;
    @FXML
    private Button SignOut;
    @FXML
    private Button Prev1;
    @FXML
    private Button getConnected;
    @FXML
    private Button Submit;
    @FXML
    private TextField IDName;
    @FXML
    private TextField IDPhone;
    @FXML
    private TextField IDEmail;
    @FXML
    private PasswordField IDPassword;
    @FXML
    private CheckBox Opos;
    @FXML
    private CheckBox Oneg;
    @FXML
    private CheckBox Apos;
    @FXML
    private CheckBox Bpos;
    @FXML
    private CheckBox ABpos;
    @FXML
    private CheckBox Aneg;
    @FXML
    private CheckBox Bneg;
    @FXML
    private CheckBox ABneg;
    @FXML
    private Button refresh;
    @FXML
    private TextArea DonnorList;
    @FXML
    private Button ViewDonnorList;
    @FXML
    private Button admin;
    @FXML
    private TextField day;
    @FXML
    private TextField month;
    @FXML
    private TextField year;
    Socket S;
    public String fullString;
    InfString P;
    Thread T;
    Hashtable<String,Inf> msg;
    DonorListController controller;
    java.util.List<TableInf> tableInfs=new ArrayList<>();
    java.util.List<Inf>Data=new ArrayList<>();
    public Controller(){
        T = new Thread(this);
        T.start();
    }
    public void run() {
        try {
            S = new Socket("localhost", 8080);
            ObjectInputStream ois = new ObjectInputStream(S.getInputStream());
            msg = (Hashtable<String, Inf>) ois.readObject();
            Enumeration e = msg.keys();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                //System.out.println(key + " " + msg.get(key).getPhone()+" " + msg.get(key).getEmail());
                TableInf tableInf=new TableInf(key,msg.get(key).getEmail(),msg.get(key).getPhone(),msg.get(key).getPassword(),msg.get(key).getBloodGr(),msg.get(key).getLastDonate(), msg.get(key).getUserStatus());
                tableInfs.add(tableInf);
                //fullString = fullString + msg.get(key).getEmail();
            }
        } catch (Exception e) {
        }
        //controller.setList(tableInfs);
    }

    @FXML
    private void LOGGING(javafx.event.ActionEvent event) throws IOException{

        Stage stage;
        Parent root;
        String InName;
        String InPassword;
        if(event.getSource()==LogIn){
            try {
                S = new Socket("localhost", 8080);
                ObjectInputStream ois = new ObjectInputStream(S.getInputStream());
                msg = (Hashtable<String, Inf>) ois.readObject();
                Enumeration e = msg.keys();
                while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();
                    System.out.println(key + " " + msg.get(key).getPhone()+" " + msg.get(key).getEmail()+" " +msg.get(key).getPassword()+" "+msg.get(key).getLastDonate());
                    TableInf tableInf = new TableInf(key, msg.get(key).getEmail(), msg.get(key).getPhone(), msg.get(key).getPassword(), msg.get(key).getBloodGr(), msg.get(key).getLastDonate(),msg.get(key).getUserStatus());
                    tableInfs.add(tableInf);
                    //fullString = fullString + msg.get(key).getEmail();
                }
            } catch (Exception e) {
            }
            InName = UserName.getText();
            InPassword = Password.getText();
            boolean t = false;
            for(TableInf m: tableInfs) {
                System.out.println(m.getName());
                System.out.println(m.getPassword());
                System.out.println(m.getUserStatus());
                try {
                    if (m.getName().equals(InName) && m.getPassword().equals(InPassword)  && m.getUserStatus().equals("Admin")) {
                        System.out.println(m.getUserStatus());
                        isUserAdmin = true;
                        userName = m.getName();
                        stage = (Stage) LogIn.getScene().getWindow();
                        root = FXMLLoader.load(getClass().getResource("AdminLogging.fxml"));
                        Scene scene = new Scene(root);

                        t = true;
                        stage.setScene(scene);
                        stage.show();

                        break;
                    }
                    else if (m.getName().equals(InName) && m.getPassword().equals(InPassword) && m.getUserStatus().equals("user")) {
                        stage = (Stage) LogIn.getScene().getWindow();
                        isUserAdmin = false;
                        userName = m.getName();
                        root = FXMLLoader.load(getClass().getResource("DonorLogging.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        t = true;
                        break;
                    }
                }catch (Exception e){}
            }
            if(!t){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("Incorrect Credentials");
                alert.setContentText("The username and password you provided is not correct.");
                alert.showAndWait();
            }
        }
        if(event.getSource()==AddNewAdmin){
            stage = (Stage) AddNewAdmin.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("NewAdmin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==ViewDonnorList){
            try {
                Socket S = new Socket("localhost", 8080);
                System.out.println("Connected to " + S.getInetAddress().getHostName());
                stage = (Stage) ViewDonnorList.getScene().getWindow();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("DonorList.fxml"));
                root=loader.load();
                DonorListController controller=loader.getController();
                System.out.println("On View Donor List");
                controller.setIsAdmin(isUserAdmin);
                controller.setUserName(userName);
                System.out.println(S.toString());
                controller.setS(S);
                System.out.println(isUserAdmin+" "+userName);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                readThread t = new readThread(S,controller);
                //writeThread st = new writeThread(S,P);

            }catch(Exception e){}
        }
        if(event.getSource()==Reset){
                UserName.setText(null);
                Password.setText(null);
        }
        if(event.getSource()==SignUp){
            stage = (Stage) SignUp.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("NewDonor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            try{
                IDName.setText("asad");
            }catch (Exception e){}



        }
        if(event.getSource()==Next){
            try {
                ClientName = (String) IDName.getText();
                password = (String) IDPassword.getText();
                System.out.println(password);
                email = (String) IDEmail.getText();
                System.out.println(email);
                phone = (String) IDPhone.getText();
                System.out.println(phone);
                datestr = (String) day.getText()+"/"+month.getText()+"/"+year.getText();
                if(Opos.isSelected()==true){
                    cnt++;
                    str="O+";
                }
                if(Oneg.isSelected()==true){
                    cnt++;
                    str="O-";
                }
                if(ABpos.isSelected()==true){
                    cnt++;
                    str="AB+";
                }
                if(ABneg.isSelected()==true){
                    cnt++;
                    str="AB-";
                }
                if(Apos.isSelected()==true){
                    cnt++;
                    str="A+";
                }
                if(Bpos.isSelected()==true){
                    cnt++;
                    str="B+";
                }
                if(Aneg.isSelected()==true){
                    cnt++;
                    str="A-";
                }
                if(Bneg.isSelected()==true){
                    cnt++;
                    str="B-";
                }

            }catch(Exception e){

            }
            if(!"".equals(ClientName)&&!"".equals(password)&&!"".equals(phone)&&!"".equals(email)&&cnt==1) {
                stage = (Stage) Next.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("Area.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if(cnt!=1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("You must select one bloodgroup.");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("Please provide all the information ");
                alert.showAndWait();
            }
        }
        if(event.getSource()==SignOut){
            stage = (Stage) SignOut.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==Submit1){
            String AdminName = IDName1.getText();
            String AdminEmail = IDEmail1.getText();
            String AdminPhone = IDPhone1.getText();
            String AdminPassword = IDPassword1.getText();
            try{
                if(Opos1.isSelected()==true){
                    cnt1++;
                    str1="O+";
                }
                if(Oneg1.isSelected()==true){
                    cnt1++;
                    str1="O-";
                }
                if(ABpos1.isSelected()==true){
                    cnt1++;
                    str1="AB+";
                }
                if(ABneg1.isSelected()==true){
                    cnt1++;
                    str1="AB-";
                }
                if(Apos.isSelected()==true){
                    cnt1++;
                    str1="A+";
                }
                if(Bpos.isSelected()==true){
                    cnt1++;
                    str1="B+";
                }
                if(Aneg.isSelected()==true){
                    cnt1++;
                    str1="A-";
                }
                if(Bneg.isSelected()==true){
                    cnt1++;
                    str1="B-";
                }

            }catch(Exception e){}
            if(!"".equals(AdminName)&&!"".equals(AdminPassword)&&!"".equals(AdminEmail)&&cnt1==1) {
                Inf L = new Inf(AdminName, AdminEmail, AdminPhone, AdminPassword, str, "Admin");
                InfString P1 = new InfString(L, "Submit");
                writeThread st = new writeThread(S);
                st.write(P1);
                readThread l = new readThread(S);
                stage = (Stage) Submit1.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if(cnt!=1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("You must select one bloodgroup.");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("Please provide all the information ");
                alert.showAndWait();
            }
        }
        if(event.getSource()==Prev1){
            stage = (Stage) Prev1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("NewDonor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==Submit){
            try {
                Socket S = new Socket("localhost", 8080);
                System.out.println("Connected to " + S.getInetAddress().getHostName());
                stage = (Stage) Submit.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                //Code for getting todays date starts here
                //Code for getting todays date ends here
                A = new Inf(ClientName, email, phone, password,str,datestr,"user");
             //   B = new TableInf(ClientName,email,phone, "user");
                P = new InfString(A, "Submit");
                writeThread st = new writeThread(S);
                st.write(P);
            }catch(Exception e){}
        }
        if(event.getSource()==Notifications){
            TestTcpSms obj = new TestTcpSms("localhost", 9500, "admin", "arnob");
        }
        if(event.getSource()==searchButton){
            try {
                Socket S = new Socket("localhost", 8080);
                System.out.println("Connected to " + S.getInetAddress().getHostName());
                 stage = (Stage) ViewDonnorList.getScene().getWindow();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("DonorList.fxml"));
                 root=loader.load();
                DonorListController controller=loader.getController();
                System.out.println("On View Donor List");
                controller.setIsAdmin(isUserAdmin);
                controller.setUserName(userName);
                controller.setSearchBooldGroup(searchTF.getText());
                System.out.println(S.toString());
                controller.setS(S);
                System.out.println(isUserAdmin+" "+userName);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                readThread t = new readThread(S,controller);
                //writeThread st = new writeThread(S,P);

            }catch(Exception e){}
        }

    }
    @FXML
    public void searchAction(ActionEvent actionEvent) {

    }


}
