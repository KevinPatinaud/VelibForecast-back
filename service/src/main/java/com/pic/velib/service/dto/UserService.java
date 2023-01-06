package com.pic.velib.service.dto;

import com.pic.velib.entity.User;
import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;

import java.util.Optional;

public interface UserService {
    public UserFacebook createUserFacebook(String accessToken);
    public UserMail createUserMail(String email , String password);
    public UserMail findUserByMail(String mail);

    public UserFacebook findUserByFacebookID(String facebookId);

}
