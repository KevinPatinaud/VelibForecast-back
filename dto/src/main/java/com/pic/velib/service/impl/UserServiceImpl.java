package com.pic.velib.service.impl;

import com.pic.velib.entity.User;
import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.repository.UserFacebookRepository;
import com.pic.velib.repository.UserMailRepository;
import com.pic.velib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserFacebookRepository userFacebookRepository;
    UserMailRepository userMailRepository;

    protected UserServiceImpl(UserFacebookRepository userFacebookRepository, UserMailRepository userMailRepository) {
        this.userFacebookRepository = userFacebookRepository;
        this.userMailRepository = userMailRepository;
    }

    @Override
    public void saveUserFacebook(UserFacebook user) {
        userFacebookRepository.save(user);
    }

    @Override
    public void saveUserMail(UserMail user) {
        userMailRepository.save(user);
    }

    @Override
    public UserMail findUserByMail(String mail) {
        return userMailRepository.findByMail(mail);
    }

    @Override
    public UserFacebook findUserByFacebookID(String facebookId) {
        return userFacebookRepository.findByFacebookId(facebookId);
    }

}
