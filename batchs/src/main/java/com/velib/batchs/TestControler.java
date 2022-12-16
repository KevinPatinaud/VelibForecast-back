package com.velib.batchs;

import com.pic.service.opendata.StationStates;
import com.pic.service.opendata.Stations;
import com.pic.velib.StationService;
import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class TestControler {

    private final StationService stationService;

    public TestControler(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping("/test")
    public int test() {
        stationService.saveStrationState(new StationState());
        return stationService.findStationStates().size();
    }
}