package com.pic.velib.service.authentication;


import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class JWTServiceImplTest {

/*
    @Test
    public void generateJWT() throws Exception {
        JWTServiceImpl jwt = new JWTServiceImpl();

        assertThat(jwt.generateJWT(123, "secret")).isEqualTo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIMjU2In0.eyJpZHVzZXIiOjEyM30.vkkMG9vRFlrQ10fvq8rNHMTJlnMyaoBfkr-ZDvT2C5g");
    }

    @Test
    public void isValidJWT() throws Exception {
        JWTServiceImpl jwt = new JWTServiceImpl();

        assertThat(jwt.isValid(jwt.generateJWT(123, "secret"), "secret")).isTrue();
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

        assertThat(jwt.getIdUser(jwt.generateJWT(123, "secret"), "secret")).isEqualTo(123);
    }
*/
}
