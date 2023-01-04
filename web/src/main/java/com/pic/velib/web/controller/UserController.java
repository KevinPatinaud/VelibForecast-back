package com.pic.velib.web.controller;

import com.pic.velib.entity.User;
import com.pic.velib.service.UserService;
import com.pic.velib.service.facebook.FacebookLogin;
import com.pic.velib.service.facebook.FacebookLoginImpl;
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

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserService userService, FacebookLogin fbLogin) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.fbLogin = fbLogin;
    }


    @GetMapping("/MailUserExist")
    public Boolean isMailUserExist(@RequestParam String id) {

       return  ! userService.findUser(id).isEmpty();

    }

    @PostMapping("/MailUser")
    public boolean createMailUser(@RequestBody Map<String, Object> params) {

        if (Recaptcha.isValide(params.get("captchaToken").toString())) {
            if ( userService.findUser(params.get("email").toString()).isEmpty() ) {
                User user = new User();
                user.setId(params.get("email").toString());
                user.setPassword(passwordEncoder.encode(params.get("password").toString()));
                user.setAuthenficationType(User.AuthenficationType.MAIL);
                userService.saveUser(user);
                return true;
            }
        }
        return false;
    }


    @PostMapping("/FacebookUser")
    public User createFacebookUser(@RequestBody Map<String, Object> params) {

        String userIdFacebook = fbLogin.confirmToken(params.get("accessToken").toString());

        User user = null;

        if ( userService.findUser(userIdFacebook).isEmpty() ) {
            if (userIdFacebook != null) {
                user = new User();
                user.setId(userIdFacebook);
                user.setAuthenficationType(User.AuthenficationType.FACEBOOK);
                userService.saveUser(user);
            }
        }
        return user;
    }

}
