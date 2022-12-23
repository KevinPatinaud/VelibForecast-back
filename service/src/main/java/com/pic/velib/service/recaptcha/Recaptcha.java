package com.pic.velib.service.recaptcha;

import com.pic.velib.entity.Station;
import com.pic.velib.service.api.Api;
import com.pic.velib.service.properties.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recaptcha {

    private static final Properties properties = new Properties();
    public static boolean isValide(String captchaToken)
    {
        String responseAPI = Api.callAPI("https://www.google.com/recaptcha/api/siteverify?secret=" + properties.getString("recaptcha_secret") + "&response=" + captchaToken);


        try {
            JSONObject tomJsonObject = new JSONObject(responseAPI);
            boolean success = tomJsonObject.getBoolean("success");

            System.out.println(success);
            return success;

        }catch(Exception e) { }
        return false;
    }
}
