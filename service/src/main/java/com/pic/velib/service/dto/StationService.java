package com.pic.velib.service.dto;

import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;

import java.util.List;


public interface StationService {


    public List<StationState> findStationStates();
    public List<StationState> findStationStates(long stationCode);


    public StationState findLastStationStates(long stationCode) ;

    void saveAllStation(List<Station> stations);

    public void saveStationState(StationState stationState);
    public void saveStation(Station station);

    public void deleteAllStationStates();

    public void updateStationState(StationState stationState);


}
