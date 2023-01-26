package com.pic.velib.web.controller;

import com.pic.velib.service.opendata.StationsAPI;
import com.pic.velib.service.opendata.StationsAPIImpl;
import com.pic.velib.service.dto.StationService;
import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/station")
public class VelibController {

    private final StationService stationService;
    private final StationsAPI stationsApi;


    @Autowired
    public VelibController(StationService stationService, StationsAPI stationsApi) {
        this.stationService = stationService;
        this.stationsApi = stationsApi;
    }


    @GetMapping("/")
    public List<Station> stations() {
        return stationService.getStations();
    }

    @GetMapping("/states")
    public List<StationState> stationStates() {

        return stationService.getStationStates();
    }



    @GetMapping("/{stationCode}/state")
    public String getStationState(@PathVariable(value="stationCode") long stationCode, @RequestParam(defaultValue = "0") int inMinutes) {
        try {
            return stationService.getStationState(stationCode , Math.max( inMinutes , 0)).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
