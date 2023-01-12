package com.pic.velib.service.facebook;

import com.pic.velib.service.api.Api;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.pic.velib.service.properties"})
public class FacebookLoginImpl implements FacebookLogin{


    @Value("${facebook_client_id}")
    private String facebook_client_id;

    @Value("${facebook_client_secret}")
    private String facebook_client_secret;

    public String confirmToken(String token) {

        String userId = null;

        String responseAPIaccessToken = Api.callAPI("https://graph.facebook.com/oauth/access_token?client_id=" + facebook_client_id+ "&client_secret=" + facebook_client_secret+ "&grant_type=client_credentials");


        try {
            String access_token = (new JSONObject(responseAPIaccessToken)).getString("access_token");



            String responseAPIDebugToken = Api.callAPI("https://graph.facebook.com/debug_token?input_token=" + token + "&access_token=" + access_token);


            userId = (new JSONObject(responseAPIDebugToken)).getJSONObject("data").getString("user_id");


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return userId;
    }
}
