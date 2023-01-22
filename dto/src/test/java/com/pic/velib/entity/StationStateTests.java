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

    public StationState generateNewStationState()
    {
        Station station = new Station();
        StationState stationState = new StationState();
        stationState.setStation(station);
        stationState.setLastReported(123456789);
        stationState.setIsRenting(true);
        stationState.setIsInstalled(true);
        stationState.setIsReturning(true);
        stationState.setNumDocksAvailable(28);
        stationState.setNumBikesAvailable(12);
        stationState.setNumBikesAvailableTypesEbike(8);
        stationState.setNumBikesAvailableTypesMechanical(4);
        stationState.setTimestampInformationGot(123456798);

        return stationState;
    }

    @Test
    public void isEqualTest()
    {
        StationState stationState = generateNewStationState();

        StationState copy = stationState;

      assert(  stationState.isEqual(copy) );
    }

}
