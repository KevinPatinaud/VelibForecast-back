package com.pic.velib.service.dto;

import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.repository.UserFacebookRepository;
import com.pic.velib.repository.UserMailRepository;
import com.pic.velib.service.dto.exception.UserAlreadyExistException;
import com.pic.velib.service.dto.exception.UserNotExistException;
import com.pic.velib.service.dto.exception.UserWrongPasswordException;
import com.pic.velib.service.facebook.FacebookLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@ComponentScan(basePackages = {"com.pic.velib.service.facebook"})
public class UserServiceImpl implements UserService {

    FacebookLogin fbLogin;

    UserFacebookRepository userFacebookRepository;
    UserMailRepository userMailRepository;


    private PasswordEncoder passwordEncoder;

    @Autowired
    protected UserServiceImpl(UserFacebookRepository userFacebookRepository, UserMailRepository userMailRepository, FacebookLogin fbLogin, PasswordEncoder passwordEncoder) {
        this.userFacebookRepository = userFacebookRepository;
        this.userMailRepository = userMailRepository;
        this.fbLogin = fbLogin;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserFacebook createUserFacebook(String accessToken) throws UserAlreadyExistException {

        String userIdFacebook = fbLogin.confirmToken(accessToken.toString());

        if (findUserByFacebookID(userIdFacebook) != null) {
            throw new UserAlreadyExistException();
        }

        if (userIdFacebook != null) {
            UserFacebook user = new UserFacebook();
            user.setFacebookId(userIdFacebook);
            return userFacebookRepository.save(user);
        }
        return null;
    }

    @Override
    public UserFacebook connectUserFacebook(String accessToken) throws UserNotExistException {

        String userIdFacebook = fbLogin.confirmToken(accessToken.toString());

        UserFacebook userFB = findUserByFacebookID(userIdFacebook);
        if (userFB == null) {
            throw new UserNotExistException();
        }

        return userFB;
    }

    @Override
    public UserMail createUserMail(String email, String password) throws UserAlreadyExistException {
        if (findUserByMail(email) != null)
            throw new UserAlreadyExistException();

        UserMail user = new UserMail();
        user.setMail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userMailRepository.save(user);
    }

    @Override
    public UserMail connectUserMail(String email, String password) throws UserNotExistException, UserWrongPasswordException {

        UserMail userDB = findUserByMail(email);

        if (userDB == null)
            throw new UserNotExistException();

        System.out.println(password);
        System.out.println(userDB.getPassword());
        System.out.println(passwordEncoder.encode(password));

        if (passwordEncoder.matches(password, userDB.getPassword()))
            return userDB;

        throw new UserWrongPasswordException();

    }

    @Override
    public UserMail findUserByMail(String mail) {
        return userMailRepository.findByMail(mail);
    }

    @Override
    public UserFacebook findUserByFacebookID(String facebookId) {
        return userFacebookRepository.findByFacebookid(facebookId);
    }

}
