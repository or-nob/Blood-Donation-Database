package Server;

import java.io.Serializable;

public class Inf implements Serializable {
    private String Name;
    private String Password;
    private String email;
    private String Phone;
    private String bloodGr;
    private String lastDonate;
    private String UserStatus;
    public Inf(String Name, String email, String Phone, String Password,String bloodGr,String lastDonate, String UserStatus){
        this.email = email;
        this.Name = Name;
        this.Phone = Phone;
        this.Password = Password;
        this.bloodGr=bloodGr;
        this.lastDonate=lastDonate;
        this.UserStatus = UserStatus;

    }
    public Inf(String Name, String email, String Phone, String UserStatus){
        this.Name = Name;
        this.email = email;
        this.Phone = Phone;
        this.UserStatus = UserStatus;
    }
    public Inf(String Name, String email, String Phone, String Password, String bloodGr, String UserStatus){
        this.email = email;
        this.Name = Name;
        this.Phone = Phone;
        this.Password = Password;
        this.bloodGr=bloodGr;
        this.UserStatus = UserStatus;
    }
    public String getName(){
        return Name;
    }
    public String getPassword(){
        return Password;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return Phone;
    }
    public String getBloodGr() {
        return bloodGr;
    }
    public String getUserStatus(){return UserStatus;}
    public String getLastDonate() {
        return lastDonate;
    }
}
