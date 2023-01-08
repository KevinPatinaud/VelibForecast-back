package com.pic.velib.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_facebook")
@PrimaryKeyJoinColumn(name = "id")
public class UserFacebook extends User {

    @Column(name = "facebookid")
    private String facebookid;

    public String getFacebookId() {
        return facebookid;
    }

    public void setFacebookId(String facebookid) {
        this.facebookid = facebookid;
    }
}
