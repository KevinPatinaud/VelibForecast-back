package com.pic.velib.service.authentication;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Component
public class JWTServiceImpl implements JWTService {
    public String generateJWT(JSONObject payload , String secret) throws Exception {

        JSONObject header = new JSONObject();
        header.put("alg", "H256");
        header.put("typ", "JWT");

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

        Base64.Encoder b64 = Base64.getUrlEncoder().withoutPadding();


        return b64.encodeToString(header.toString().getBytes()) + "." +
                b64.encodeToString(payload.toString().getBytes()) + "." +
                b64.encodeToString(
                        sha256.digest( (
                                header.toString() + "." +
                                payload.toString() + "." +
                                secret
                        ).getBytes() ))
                ;
    }

    public boolean isValid(String jwtToken, String secret)  {
        try {
            String[] data = jwtToken.split("\\.");

            if (data.length != 3)
                return false;

            Base64.Decoder b64 = Base64.getUrlDecoder();
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");


            return Arrays.equals(sha256.digest((new String(b64.decode(data[0])) + "." + new String(b64.decode(data[1])) + "." + secret).getBytes()), b64.decode(data[2]));
        }catch(Exception e)
        {
            return false;
        }
    }

    protected JSONObject getPayload(String jwtToken, String secret)
    {
        if ( ! isValid(jwtToken, secret) )
            return null;

        String[] data = jwtToken.split("\\.");


        Base64.Decoder b64 = Base64.getUrlDecoder();
        try {
            return new JSONObject( new String(b64.decode(data[1])) );
        } catch (JSONException e) {
            return null;
        }

    }


    public int getIdUser(String jwtToken, String secret)  {
        try {
            return getPayload( jwtToken,  secret).getInt("iduser");
        } catch (JSONException e) {
            return -1;
        }
    }
}
