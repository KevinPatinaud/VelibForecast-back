package com.pic.velib.service.properties;

import org.json.JSONObject;
import org.springframework.boot.jdbc.DataSourceBuilder;

import java.io.*;

public class Properties {
    private JSONObject properties;

    public Properties()
    {
        String urlPropertiesFile = System.getProperty("user.dir") + "/properties.json";

        System.out.println(urlPropertiesFile);

        try {
            File f = new File(urlPropertiesFile);
            if (f.exists()) {
                InputStream is = new FileInputStream(urlPropertiesFile);

                StringBuilder resultStringBuilder = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line);
                }

                String jsonTxt = resultStringBuilder.toString();

                properties = new JSONObject(jsonTxt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String prop)
    {
        try {
            return properties.getString(prop);
        }catch(Exception e) {
            return null;
        }
    }
}
