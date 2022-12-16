package com.pic.velib;


import com.pic.velib.entity.Station;
import com.pic.velib.repository.StationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StationRepositoryTest {

    @Autowired
    StationRepository stationRepository;

    @Before
    public void setUp()
    {
        stationRepository.deleteAll();
    }

    @Test
    public void simpleSave()
    {
        Station s = createStation();
        stationRepository.save(s);

        assertThat(stationRepository.findAll()).hasSize(1);
    }

    public Station createStation()
    {
        Station station = new Station();
        station.setCapacity(18);
        station.setStationCode(123456);
        station.setLatitude(48.85);
        station.setLongitude(2.23);

        return station;
    }
}
