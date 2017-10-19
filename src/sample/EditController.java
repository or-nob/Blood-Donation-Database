package sample;

import Server.Inf;
import Server.InfString;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suvo on 14-Dec-15.
 */
public class EditController {
    Socket S;
    public TextField IDPhone;
    public Button edit;
    List<TableInf> listInfs=new ArrayList<>();
    TableInf editable;
    boolean isAdmin;
    public TextField IDName;
    public TextField IDEmail;
    public PasswordField IDPassword;
    public CheckBox Oneg;
    public CheckBox Opos;
    public CheckBox Aneg;
    public CheckBox Apos;
    public CheckBox Bneg;
    public CheckBox Bpos;
    public CheckBox ABneg;
    public CheckBox ABpos;
    public TextField Date;
    /*public TextField Month;
    public TextField Year;*/

    public void bgChange(ActionEvent actionEvent) {
    }

    public void onEdit(ActionEvent actionEvent) {
        editable.setEmail(IDEmail.getText());
        editable.setPhone(IDPhone.getText());
        editable.setPassword(IDPassword.getText());
        editable.setLastDonate(Date.getText());
        List<Inf> infs=new ArrayList<>();
        for(TableInf tinf:listInfs){
            Inf inf=new Inf(tinf.getName(),tinf.getEmail(),tinf.getPhone(),tinf.getPassword(), tinf.getBloodGr(), tinf.getLastDonate(),tinf.getUserStatus());
            infs.add(inf);
        }
        writeThread st=new writeThread(S);
        InfString infString=new InfString(infs.get(0),"OnEdit");
        st.write(infString);
        for(Inf inf:infs){
            InfString infstring=new InfString(inf,"Submit");
            st.write(infstring);
        }
        try {
            S = new Socket("localhost", 8080);
            System.out.println("Connected to " + S.getInetAddress().getHostName());
            Stage stage = (Stage) edit.getScene().getWindow();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DonorList.fxml"));
            Parent root=loader.load();
            DonorListController controller=loader.getController();
            System.out.println("On Edit");
            controller.setIsAdmin(isAdmin);
            controller.setS(S);

            controller.setUserName(editable.getName());
            controller.setList(listInfs);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            //writeThread st = new writeThread(S,P);

        }catch(Exception e){}


    }

    public void setListInfs(List<TableInf> listInfs) {
        this.listInfs = listInfs;
    }

    public void setEditable(TableInf editable) {
        this.editable = editable;
        IDName.setText(editable.getName()+"("+editable.getUserStatus()+")");
        IDEmail.setText(editable.getEmail());
        IDPassword.setText(editable.getPassword());
        IDPhone.setText(editable.getPhone());
        Date.setText(editable.getLastDonate());
        /*
        switch(editable.getBloodGroup(){
        }
         */
        IDName.setEditable(false);

    }

    public void setS(Socket s) {

        S = s;
        System.out.println(S.toString());
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
