package com.pic.velib.web.controller;

import com.pic.velib.entity.User;
import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.service.UserService;
import com.pic.velib.service.facebook.FacebookLogin;
import com.pic.velib.service.recaptcha.Recaptcha;
import com.pic.velib.service.recaptcha.RecaptchaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    private PasswordEncoder passwordEncoder;

    private FacebookLogin fbLogin;

    private final UserService userService;

    private Recaptcha recaptcha;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserService userService, FacebookLogin fbLogin, Recaptcha recaptcha) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.fbLogin = fbLogin;
        this.recaptcha = recaptcha;
    }


    @GetMapping("/MailUserExist")
    public Boolean isMailUserExist(@RequestParam String id) {

       return  ! userService.findUser(id).isEmpty();

    }

    @PostMapping("/MailUser")
    public boolean createMailUser(@RequestBody Map<String, Object> params) {

        if (recaptcha.isValide(params.get("captchaToken").toString())) {
            if ( userService.findUser(params.get("email").toString()).isEmpty() ) {
                UserMail user = new UserMail();
                user.setMail(params.get("email").toString());
                user.setPassword(passwordEncoder.encode(params.get("password").toString()));
                userService.saveUser(user);
                return true;
            }
        }
        return false;
    }


    @PostMapping("/FacebookUser")
    public boolean createFacebookUser(@RequestBody Map<String, Object> params) {

        String userIdFacebook = fbLogin.confirmToken(params.get("accessToken").toString());

        if ( userService.findUser(userIdFacebook).isEmpty() ) {
            if (userIdFacebook != null) {
                UserFacebook user = new UserFacebook();
                user.setFacebookId(userIdFacebook);
                userService.saveUser(user);
                return true;
            }
        }
        return false;
    }

}
