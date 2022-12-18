package com.pic.velib.web.config;

import org.json.JSONObject;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.io.*;

@Configuration
@Profile("prod")
public class DataSourceProdConfig {

    @Bean
    public DataSource getDataSource() {
        String propertiesFile = "/var/SpringServer/properties.json";

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();



        try {
            File f = new File(propertiesFile);
            if (f.exists()) {
                InputStream is = new FileInputStream(propertiesFile);

                StringBuilder resultStringBuilder = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line);
                }

                String jsonTxt = resultStringBuilder.toString();

                JSONObject json = new JSONObject(jsonTxt);

                dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
                dataSourceBuilder.url(json.getString("database_url"));
                dataSourceBuilder.username(json.getString("database_user"));
                dataSourceBuilder.password(json.getString("database_password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSourceBuilder.build();
    }

}