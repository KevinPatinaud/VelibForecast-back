package com.pic.velib.service.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Api {

    public static String callAPI(String URL) {

        try {
            String content = "";
            String s;

            System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36");
            BufferedReader r = new BufferedReader(new InputStreamReader(new URL(URL).openStream()));

            while ((s = r.readLine()) != null) {
                content = content + s;
            }

            return content;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
