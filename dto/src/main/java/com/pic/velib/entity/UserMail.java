package com.pic.velib.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="user_mail")
@PrimaryKeyJoinColumn(name = "id")
public class UserMail extends User{

    private String mail;

    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
