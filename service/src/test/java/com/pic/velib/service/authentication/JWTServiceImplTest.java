package com.pic.velib.service.authentication;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class JWTServiceImplTest {


    @Test
    public void generateJWT() throws Exception {
        JWTServiceImpl jwt = new JWTServiceImpl();

        assertThat(jwt.generateJWT(new JSONObject("{idUser = 123456}"), "secret")).isEqualTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIMjU2In0.eyJpZFVzZXIiOjEyMzQ1Nn0.Ia9KDU_ZJymPWtmLjWCg3CDb-I127rx9N40noxXiP34");
    }

    @Test
    public void isValidJWT() throws Exception {
        JWTServiceImpl jwt = new JWTServiceImpl();

        assertThat(jwt.isValid(jwt.generateJWT(new JSONObject("{idUser = 123456}"), "secret"), "secret")).isTrue();
    }

    @Test
    public void isNotValidJWT() throws Exception {
        JWTServiceImpl jwt = new JWTServiceImpl();

        assertThat(jwt.isValid("a.b.c", "secret")).isFalse();
    }

    @Test
    public void isNotWellformattedJWT() throws Exception {
        JWTServiceImpl jwt = new JWTServiceImpl();

        assertThat(jwt.isValid("a", "secret")).isFalse();
    }


    @Test
    public void getUserId() throws Exception {
        JWTServiceImpl jwt = new JWTServiceImpl();
        JSONObject toTest = new JSONObject("{idUser = 123456}");

        assertThat(jwt.getPayload(jwt.generateJWT(toTest, "secret"), "secret").toString() ).isEqualTo(toTest.toString());
    }

}
