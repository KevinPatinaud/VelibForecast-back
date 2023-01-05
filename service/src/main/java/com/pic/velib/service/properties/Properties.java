package com.pic.velib.service.properties;

import java.util.Map;

public interface Properties {
    public  String getDatabaseURL();
    public  String getDatabaseUser();
    public  String getDatabasePassword();
    public  String getRecaptchaSecret();
    public String getFacebookClientID();
    public String getFacebookClientSecret();
    public Map getSpringAppDefaultProperties();
}
