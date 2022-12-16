package com.pic.velib;

import com.pic.velib.entity.StationState;
import com.pic.velib.repository.StationStateRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public interface StationService {


    public List<StationState> findStationStates();
    public List<StationState> findStationStates(long stationCode);

    public void saveStrationState(StationState stationState);

    public void deleteAllStationStates();

    public void updateStationState(StationState stationState);


}
