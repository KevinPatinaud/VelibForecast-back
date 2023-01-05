package com.pic.velib.service;

import com.pic.velib.entity.User;
import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;

import java.util.Optional;

public interface UserService {
    public void saveUserFacebook(UserFacebook user);
    public void saveUserMail(UserMail user);
    public UserMail findUserByMail(String mail);

    public UserFacebook findUserByFacebookID(String facebookId);

}
