package com.pic.velib.service.opendata;

import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;

import java.util.List;

public interface StationsAPI {

    public List<Station> getStations();

    public List<Station> getStations(StationsAPIImpl.StateHistoryDeepth stateHistoryDeepth);

    public  List<StationState> getCurrentStates();

}
