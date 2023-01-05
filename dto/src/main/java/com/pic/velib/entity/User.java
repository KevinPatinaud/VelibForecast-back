package com.pic.velib.entity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

  public enum AuthenficationType {
        FACEBOOK,
        MAIL
    }

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private AuthenficationType authenficationtype;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AuthenficationType getAuthenficationType() {
        return authenficationtype;
    }

    public void setAuthenficationType(AuthenficationType authenficationType) {
        this.authenficationtype = authenficationType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
