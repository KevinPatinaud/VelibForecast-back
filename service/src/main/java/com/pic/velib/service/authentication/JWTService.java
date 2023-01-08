package com.pic.velib.service.authentication;

import org.json.JSONObject;

public interface JWTService {
    public String generateJWT(JSONObject payload , String secret) throws Exception;
}
