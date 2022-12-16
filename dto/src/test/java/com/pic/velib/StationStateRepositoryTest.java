package com.pic.velib;

import com.pic.velib.entity.StationState;
import com.pic.velib.repository.StationStateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StationStateRepositoryTest {

    @Autowired
    private StationStateRepository stationStateRepository;

    @Before
    public void setUp() {
        stationStateRepository.deleteAll();
    }

    @Test
    public void shouldSaveStation() {
        StationState s = createStation(123456789);
        stationStateRepository.save(s);
        assertThat(stationStateRepository.findAll()).hasSize(1);
        assertThat(stationStateRepository.findByStationCodeOrderByLastReportedDesc(123456789).get(0).getStationCode()).isEqualTo(123456789);
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
