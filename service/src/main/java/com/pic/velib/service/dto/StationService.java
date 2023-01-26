package com.pic.velib.service.dto;

import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;
import org.json.JSONObject;

import java.util.List;


public interface StationService {


    StationState findLastStationStates(long stationCode);

    void deleteStationStatesBefore(long timestamp);

    void saveAllStation(List<Station> stations);


    List<StationState> findStationStatesBefore(long timestamp);


    void updateStationState(StationState stationState);

    List<Station> getStations();
    List<StationState> getStationStates();

    JSONObject getStationState(long stationCode, int inMinutes) throws Exception;
}
