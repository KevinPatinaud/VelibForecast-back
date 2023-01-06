package com.pic.velib.service.authentication;


import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class JWTUtilTest {


    @Test
    public void generateJWT() throws Exception {
        JWTUtil jwt = new JWTUtil();

        assertThat(jwt.generateJWT(123, "secret")).isEqualTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIMjU2In0.eyJpZHVzZXIiOjEyM30.vkkMG9vRFlrQ10fvq8rNHMTJlnMyaoBfkr-ZDvT2C5g");
    }

    @Test
    public void isValidJWT() throws Exception {
        JWTUtil jwt = new JWTUtil();

        assertThat(jwt.isValid(jwt.generateJWT(123, "secret"), "secret")).isTrue();
    }

    @Test
    public void isNotValidJWT() throws Exception {
        JWTUtil jwt = new JWTUtil();

        assertThat(jwt.isValid("a.b.c", "secret")).isFalse();
    }

    @Test
    public void isNotWellformattedJWT() throws Exception {
        JWTUtil jwt = new JWTUtil();

        assertThat(jwt.isValid("a", "secret")).isFalse();
    }


    @Test
    public void getUserId() throws Exception {
        JWTUtil jwt = new JWTUtil();

        assertThat(jwt.getIdUser(jwt.generateJWT(123, "secret"), "secret")).isEqualTo(123);
    }

}
