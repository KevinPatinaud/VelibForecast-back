package com.pic.velib.service.recaptcha;

import com.pic.velib.service.api.Api;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RecaptchaImpl implements Recaptcha{


    @Value("${recaptcha_secret}")
    private String recaptcha_secret;


    public boolean isValide(String captchaToken)
    {
        String responseAPI = Api.callAPI("https://www.google.com/recaptcha/api/siteverify?secret=" + recaptcha_secret+ "&response=" + captchaToken);


        try {
            JSONObject tomJsonObject = new JSONObject(responseAPI);
            boolean success = tomJsonObject.getBoolean("success");

            return success;

        }catch(Exception e) { }
        return false;
    }
}
