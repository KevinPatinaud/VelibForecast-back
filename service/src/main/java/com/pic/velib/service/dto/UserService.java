package com.pic.velib.service.dto;

import com.pic.velib.entity.User;
import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.service.dto.exception.UserAlreadyExistException;
import com.pic.velib.service.dto.exception.UserNotExistException;
import com.pic.velib.service.dto.exception.UserWrongPasswordException;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);

    UserFacebook createUserFacebook(String accessToken) throws UserAlreadyExistException;

    UserMail createUserMail(String email, String password) throws UserAlreadyExistException;

    UserFacebook getUserFacebook(String accessToken) throws UserNotExistException;

    UserMail getUserMail(String mail);

    UserFacebook findUserByFacebookID(String facebookId);

    void addFavoriteStation(int id_station, UUID id_user) throws UserNotExistException;


    void removeFavoriteStation(int id_station, UUID id_user) throws UserNotExistException;
}
