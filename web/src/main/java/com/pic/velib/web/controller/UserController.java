package com.pic.velib.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pic.velib.entity.User;
import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.service.authentication.JWTService;
import com.pic.velib.service.dto.exception.UserAlreadyExistException;
import com.pic.velib.service.dto.exception.UserNotExistException;
import com.pic.velib.service.dto.UserService;
import com.pic.velib.service.dto.exception.UserWrongPasswordException;
import com.pic.velib.service.facebook.FacebookLogin;
import com.pic.velib.service.recaptcha.Recaptcha;
import com.pic.velib.web.exception.UserAlreadyExistHTTPException;
import com.pic.velib.web.exception.UserNotExistHTTPException;
import com.pic.velib.web.exception.UserWrongPasswordHTTPException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    private PasswordEncoder passwordEncoder;

    private FacebookLogin fbLogin;

    private final UserService userService;

    private Recaptcha recaptcha;

    private JWTService jwt;

    private String jwtSecret;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserService userService, FacebookLogin fbLogin, Recaptcha recaptcha, JWTService jwt) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.fbLogin = fbLogin;
        this.recaptcha = recaptcha;
        this.jwt = jwt;
        try {
            jwtSecret = String.valueOf(SecureRandom.getInstanceStrong().nextLong());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/MailUser/exist")
    public Boolean isMailUserExist(@RequestParam String mail) {

        return userService.findUserByMail(mail) != null;

    }

    @PostMapping("/MailUser")
    public String createMailUser(@RequestBody Map<String, Object> params) {

        if (!recaptcha.isValide(params.get("captchaToken").toString())) return null;


        try {
            UserMail user = userService.createUserMail(params.get("email").toString(), params.get("password").toString());
            return generateResponseUserConnected(user).toString();
        } catch (UserAlreadyExistException e) {
            throw new UserAlreadyExistHTTPException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/MailUser")
    public String connectMailUser(@RequestBody Map<String, Object> params) {

        if (!recaptcha.isValide(params.get("captchaToken").toString())) return null;


        try {
            UserMail user = userService.connectUserMail(params.get("email").toString(), params.get("password").toString());
            return generateResponseUserConnected(user).toString();
        } catch (UserNotExistException e) {
            throw new UserNotExistHTTPException(e);
        } catch (UserWrongPasswordException e) {
            throw new UserWrongPasswordHTTPException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }


    @PostMapping("/FacebookUser")
    public String createFacebookUser(@RequestBody Map<String, Object> params) {

        try {
            UserFacebook user = userService.createUserFacebook(params.get("accessToken").toString());
            return generateResponseUserConnected(user).toString();
        } catch (UserAlreadyExistException e) {
            throw new UserAlreadyExistHTTPException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


    @PutMapping("/FacebookUser")
    public String connectFacebookUser(@RequestBody Map<String, Object> params) {


        try {
            UserFacebook user = userService.connectUserFacebook(params.get("accessToken").toString());
            return generateResponseUserConnected(user).toString();
        } catch (UserNotExistException e) {
            throw new UserNotExistHTTPException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }


    private JSONObject generateResponseUserConnected(User user) throws JSONException {

        if (user == null) return null;

        JSONObject response = new JSONObject();

        JSONObject payload = new JSONObject();
        payload.put("iduser" , user.getId());
     //   payload.put("favoriteStations" , user.getFavoriteStations());

        try {
            response.put("JWT", jwt.generateJWT(payload, jwtSecret));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }


}
