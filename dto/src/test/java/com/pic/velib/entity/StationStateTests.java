package com.pic.velib.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader= AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class StationStateTests {

    @Configuration
    static class ContextConfiguration {

    }

    @Test
    public void isEqual()
    {
        Station station = new Station();

        StationState stationState = new StationState();
        stationState.setStation(station);

        StationState copy = stationState;

      assert(  stationState.isEqual(copy) );
    }

}
