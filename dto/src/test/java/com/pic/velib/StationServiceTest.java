package com.pic.velib;


import com.pic.velib.entity.StationState;
import com.pic.velib.service.StationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StationServiceTest {

    @Autowired
    private StationService stationService;

    @Before
    public void setUp()
    {
        stationService.deleteAllStationStates();
    }

    @Test
    public void shouldSaveNewStationWhenUpdated() {
        StationState stationState = createStation(465);
        stationService.updateStationState(stationState);
        assertThat(stationService.findStationStates(465)).hasSize(1);
    }

    @Test
    public void shouldUpdateStationWhenDataAreUpdated()
    {
        StationState stationState = createStation(465);
        stationState.setNumDocksAvailable(4);
        stationState.setLastReported(1);
        stationService.saveStationState(stationState);


        StationState stationStateUpdated = createStation(465);
        stationStateUpdated.setNumDocksAvailable(6);
        stationState.setLastReported(2);
        stationService.updateStationState(stationStateUpdated);


        assertThat(stationService.findStationStates(465)).hasSize(2);
        assertThat(stationService.findStationStates(465).get(0).getNumDocksAvailable()).isEqualTo(6);
    }

    @Test
    public void shouldntUpdateStationWhenDataArentUpdated()
    {
        StationState stationState = createStation(465);
        stationState.setNumDocksAvailable(4);
        stationState.setLastReported(1);
        stationService.saveStationState(stationState);


        StationState stationStateUpdated = createStation(465);
        stationStateUpdated.setNumDocksAvailable(4);
        stationState.setLastReported(2);
        stationService.updateStationState(stationStateUpdated);


        assertThat(stationService.findStationStates(465)).hasSize(1);
        assertThat(stationService.findStationStates(465).get(0).getNumDocksAvailable()).isEqualTo(4);
    }



    private StationState createStation(long stationCode) {
        StationState s = new StationState();
        s.setStationCode(stationCode);
        s.setIsInstalled(true);
        s.setIsRenting(true);
        s.setLastReported(132456);
        s.setNumBikesAvailable(5);
        s.setNumBikesAvailableTypesEbike(2);
        s.setNumBikesAvailableTypesMechanical(3);
        s.setNumDocksAvailable(10);

        return s;
    }

}



