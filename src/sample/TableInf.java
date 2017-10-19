package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ASUS on 12/7/2015.
 */
public class TableInf {
    private String Name;
    private String Password;
    private String email;
    private String Phone;
    private SimpleStringProperty sspName;
    private SimpleStringProperty sspEmail;
    private SimpleStringProperty sspPnone;
    private SimpleStringProperty sspBloodGroup;
    private SimpleStringProperty sspLastDonate;
    private String bloodGr;
    private String lastDonate;
    private String Status;

    //String
    public TableInf(String Name, String email, String Phone, String Password,String bloodGr,String lastDonate, String Status){
        this.email = email;
        this.Name = Name;
        this.Phone = Phone;
        this.Password = Password;
        this.bloodGr = bloodGr;
        this.lastDonate = lastDonate;
        this.Status = Status;
        this.bloodGr=bloodGr;
        sspName=new SimpleStringProperty(Name);
        sspEmail=new SimpleStringProperty(email);
        sspPnone=new SimpleStringProperty(Phone);
        sspBloodGroup=new SimpleStringProperty(bloodGr);
        sspLastDonate=new SimpleStringProperty(lastDonate);


    }



    public String getName(){
        return Name;
    }
    public String getUserStatus(){return Status;}
    String getPassword(){
        return Password;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return Phone;
    }
    public String getLastDonate(){ return lastDonate;}
    public String getSspName() {
        return sspName.get();
    }

    public SimpleStringProperty sspNameProperty() {
        return sspName;
    }

    public String getSspEmail() {
        return sspEmail.get();
    }

    public SimpleStringProperty sspEmailProperty() {
        return sspEmail;
    }

    public String getSspPnone() {
        return sspPnone.get();
    }

    public SimpleStringProperty sspPnoneProperty() {
        return sspPnone;
    }

    public String getSspBloodGroup() {
        return sspBloodGroup.get();
    }

    public SimpleStringProperty sspBloodGroupProperty() {
        return sspBloodGroup;
    }

    public String getSspLastDonate() {
        return sspLastDonate.get();
    }

    public SimpleStringProperty sspLastDonateProperty() {
        return sspLastDonate;
    }


    public void setPassword(String password) {
        Password = password;
    }

    public void setBloodGr(String bloodGr) {
        this.bloodGr = bloodGr;
    }
    public void setLastDonate(String lastDonate){this.lastDonate = lastDonate;}

    public String getBloodGr() {
        return bloodGr;
    }

    public void setEmail(String email) {
        this.email = email;
        sspEmail=new SimpleStringProperty(email);
    }

    public void setPhone(String phone) {
        Phone = phone;
        sspPnone=new SimpleStringProperty(Phone);
    }
}
