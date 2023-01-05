package com.pic.velib.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_facebook")
@PrimaryKeyJoinColumn(name = "id")
public class UserFacebook extends User {

    @Column(name = "facebook_id")
    private String facebookId;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
