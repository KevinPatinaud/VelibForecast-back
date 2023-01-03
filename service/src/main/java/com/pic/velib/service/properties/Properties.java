package com.pic.velib.service.properties;

public interface Properties {
    public  String getDatabaseURL();
    public  String getDatabaseUser();
    public  String getDatabasePassword();
    public  String getRecaptchaSecret();


    String getFacebookClientID();

    String getFacebookClientSecret();
}
