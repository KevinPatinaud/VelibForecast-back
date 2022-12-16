package com.velib.batchs.task;

import com.pic.service.opendata.StationStates;
import com.pic.velib.StationService;
import com.pic.velib.entity.StationState;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class StationStatesTask {

    private final StationService stationService;

    public StationStatesTask(StationService stationService) {
        this.stationService = stationService;
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void getStations() {
        List<StationState> stationStates = StationStates.getCurrentStates();
        System.out.println(stationStates.size());

        stationService.saveStrationState( new StationState());
        System.out.println( stationService.findStationStates().size());
    //    System.out.println(stationService.findStationStates(14111).size());


    }


}