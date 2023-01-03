package com.pic.velib.service.recaptcha;

import com.pic.velib.service.api.Api;
import com.pic.velib.service.properties.PropertiesImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class Recaptcha {

    @Autowired
    private static PropertiesImpl properties;
    public static boolean isValide(String captchaToken)
    {
        String responseAPI = Api.callAPI("https://www.google.com/recaptcha/api/siteverify?secret=" + properties.getRecaptchaSecret() + "&response=" + captchaToken);


        try {
            JSONObject tomJsonObject = new JSONObject(responseAPI);
            boolean success = tomJsonObject.getBoolean("success");

            System.out.println(success);
            return success;

        }catch(Exception e) { }
        return false;
    }
}
