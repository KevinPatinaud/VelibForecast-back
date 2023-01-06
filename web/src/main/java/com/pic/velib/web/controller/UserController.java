package com.pic.velib.web.controller;

import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.service.dto.UserService;
import com.pic.velib.service.facebook.FacebookLogin;
import com.pic.velib.service.recaptcha.Recaptcha;
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


    @GetMapping("/MailUser/exist")
    public Boolean isMailUserExist(@RequestParam String mail) {

       return  userService.findUserByMail(mail) != null;

    }

    @PostMapping("/MailUser")
    public UserMail createMailUser(@RequestBody Map<String, Object> params) {

        if (recaptcha.isValide(params.get("captchaToken").toString())) {
            return userService.createUserMail(params.get("email").toString() , params.get("password").toString()) ;
        }
        return null;
    }


    @PostMapping("/FacebookUser")
    public UserFacebook createFacebookUser(@RequestBody Map<String, Object> params) {

        return userService.createUserFacebook(params.get("accessToken").toString());

    }

}
