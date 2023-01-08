package com.pic.velib.service.dto;

import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.service.dto.exception.UserAlreadyExistException;
import com.pic.velib.service.dto.exception.UserNotExistException;
import com.pic.velib.service.dto.exception.UserWrongPasswordException;

public interface UserService {
    public UserFacebook createUserFacebook(String accessToken) throws UserAlreadyExistException;
    public UserMail createUserMail(String email , String password) throws UserAlreadyExistException;
    public UserFacebook connectUserFacebook(String accessToken) throws UserNotExistException;
    public UserMail connectUserMail(String email , String password) throws UserNotExistException, UserWrongPasswordException;
    public UserMail findUserByMail(String mail);

    public UserFacebook findUserByFacebookID(String facebookId);

}
