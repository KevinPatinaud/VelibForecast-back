package com.pic.velib.web.controller;

import com.pic.velib.entity.User;
import com.pic.velib.service.UserService;
import com.pic.velib.service.properties.Properties;
import com.pic.velib.service.recaptcha.Recaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/MailUserExist")
    public Boolean isMailUserExist(@RequestParam String id) {

       return  ! userService.findUser(id).isEmpty();

    }

    @PostMapping("/MailUser")
    public boolean createMailUser(@RequestBody Map<String, Object> params) {

        // https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt

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
    public User createFacebookUser() {

        /*


https://developers.facebook.com/docs/facebook-login/guides/advanced/manual-flow


1/ Récuperer le token pour interroger l'API Facebook

	 https://graph.facebook.com/oauth/access_token
  ?client_id={your-app-id}
  &client_secret={your-app-secret}
  &grant_type=client_credentials


  	 https://graph.facebook.com/oauth/access_token?client_id=684735199895720&client_secret=f76a21faeb0f574e5de95a14c217b8a8&grant_type=client_credentials


2/ Valider que le token d'authentification renvoyé par l'utilisteur est bien valide ( accessToken ).
Facebook ne retournera alors que l'userID, il faut donc se baser sur cette pour la clef d'identification et non sur l'adresse mail

    GET graph.facebook.com/debug_token?
     input_token={token-to-inspect}
     &access_token={app-token-or-admin-token}


	 https://graph.facebook.com/debug_token?input_token=EAAJuw1ZAa4KgBAERnaHyBCm1R5dXvENGgkyUTVDKNbeak6fmYDNC2ZBg1YgxPAZAtao0viQEzb9A0hZBoTv2BqZBrdFNC9f4traqDxJv5IhZBYWCqX0EBpfJjaZCktOueZCjW2DIj9L8fRCYqE5fJriad3vDK60wUEsCFeETGoFKrTBQtEcyU8ePQySY4hNt3bpNK72YZC0Pc6wZDZD&access_token=684735199895720|bohRFvL8oxmk5J5ZZi_84dcqIAA

   */
        User user = new User();

        user.setId("123456789");

        userService.saveUser(user);
        return user;
    }

}
