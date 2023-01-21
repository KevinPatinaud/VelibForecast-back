package com.pic.velib.entity;


import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class StationTests {

    @Configuration
    static class ContextConfiguration {

    }

    @Test
    public void toJSON() {

        Station station = new Station();

        station.setStationCode(123456);
        station.setLatitude(42.36);
        station.setLongitude(12.89);
        station.setRentalMethods("credit card");
        station.setName("Eiffel tower");
        station.setCapacity(50);
        station.setTimeStampInformationGot(10000003);

        JSONObject json = station.toJSON();

        System.out.println(json.toString());

        assert ((json.toString()).equals("{\"stationCode\":123456,\"timeStampInformationGot\":10000003,\"latitude\":42.36,\"name\":\"Eiffel tower\",\"rentalMethods\":\"credit card\",\"longitude\":12.89,\"capacity\":50}"));
    }


}
