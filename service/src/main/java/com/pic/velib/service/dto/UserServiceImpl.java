package com.pic.velib.service.dto;

import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.repository.UserFacebookRepository;
import com.pic.velib.repository.UserMailRepository;
import com.pic.velib.service.dto.UserService;
import com.pic.velib.service.facebook.FacebookLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = {"com.pic.velib.service.facebook"})
public class UserServiceImpl implements UserService {

    FacebookLogin fbLogin;

    UserFacebookRepository userFacebookRepository;
    UserMailRepository userMailRepository;

    @Autowired
    protected UserServiceImpl(UserFacebookRepository userFacebookRepository, UserMailRepository userMailRepository, FacebookLogin fbLogin) {
        this.userFacebookRepository = userFacebookRepository;
        this.userMailRepository = userMailRepository;
        this.fbLogin = fbLogin;
    }

    @Override
    public UserFacebook createUserFacebook(String accessToken) {

        String userIdFacebook = fbLogin.confirmToken(accessToken.toString());

        if (findUserByFacebookID(userIdFacebook) == null) {
            if (userIdFacebook != null) {
                UserFacebook user = new UserFacebook();
                user.setFacebookId(userIdFacebook);
                return userFacebookRepository.save(user);
            }
        }
        return null;
    }

    @Override
    public UserMail createUserMail(String email, String password) {
        if (findUserByMail(email) != null)
            return null;

        UserMail user = new UserMail();
        user.setMail(email);
        user.setPassword(password);
        return userMailRepository.save(user);
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
