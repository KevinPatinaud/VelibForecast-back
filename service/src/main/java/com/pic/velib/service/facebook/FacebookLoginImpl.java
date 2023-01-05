package com.pic.velib.service.facebook;

import com.pic.velib.service.api.Api;
import com.pic.velib.service.properties.Properties;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.pic.velib.service.properties"})
public class FacebookLoginImpl implements FacebookLogin{

    private Properties properties;

    @Autowired
    public FacebookLoginImpl(Properties properties) {
        this.properties = properties;
    }

    public String confirmToken(String token) {

        String userId = null;

        String responseAPIaccessToken = Api.callAPI("https://graph.facebook.com/oauth/access_token?client_id=" + properties.getFacebookClientID() + "&client_secret=" + properties.getFacebookClientSecret() + "&grant_type=client_credentials");

        System.out.println(responseAPIaccessToken);

        try {
            String access_token = (new JSONObject(responseAPIaccessToken)).getString("access_token");


            System.out.println("access_token : " + access_token);

            String responseAPIDebugToken = Api.callAPI("https://graph.facebook.com/debug_token?input_token=" + token + "&access_token=" + access_token);


            System.out.println("responseAPIDebugToken : " + responseAPIDebugToken);

            userId = (new JSONObject(responseAPIDebugToken)).getJSONObject("data").getString("user_id");

            System.out.println("userId : " + userId);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return userId;
    }
}
