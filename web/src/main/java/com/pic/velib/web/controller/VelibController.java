package com.pic.velib.web.controller;

import com.pic.service.opendata.StationStates;
import com.pic.service.opendata.Stations;
import com.pic.velib.service.StationService;
import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@CrossOrigin
public class VelibController {

    private final StationService stationService;

    public VelibController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping("/stationStates")
    public List<StationState> stationStates() {

        return StationStates.getCurrentStates();
    }

    @GetMapping("/stations")
    public List<Station> stations() {
        return Stations.getStations();
    }

    @GetMapping("/test")
    public int test() {
        stationService.saveStrationState( new StationState());
        return stationService.findStationStates().size();
    }

}
