package com.pic.velib.service.opendata;

import com.pic.velib.service.api.Api;
import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StationsAPIImpl implements StationsAPI {


    public enum StateHistoryDeepth {none, current, all}


    public List<Station> getStations() {
        return getStations(StateHistoryDeepth.none);
    }

    public List<Station> getStations(StateHistoryDeepth stateHistoryDeepth) {
        String responseAPI = Api.callAPI("https://velib-metropole-opendata.smoove.pro/opendata/Velib_Metropole/station_information.json");
        List<Station> stations = new ArrayList<Station>();


        try {
            JSONObject tomJsonObject = new JSONObject(responseAPI);

            JSONArray listStationsJSON = tomJsonObject.getJSONObject("data").getJSONArray("stations");


            for (int i = 0; i < listStationsJSON.length(); i++) {
                try {
                    Station station = new Station();

                    station.setStationCode(listStationsJSON.getJSONObject(i).getLong("stationCode"));
                    station.setName(listStationsJSON.getJSONObject(i).getString("name"));
                    station.setLatitude(listStationsJSON.getJSONObject(i).getDouble("lat"));
                    station.setLongitude(listStationsJSON.getJSONObject(i).getDouble("lon"));
                    station.setCapacity(listStationsJSON.getJSONObject(i).getInt("capacity"));

                    if (listStationsJSON.getJSONObject(i).has("rental_methods"))
                        station.setRentalMethods(listStationsJSON.getJSONObject(i).getJSONArray("rental_methods").getString(0));


                    stations.add(station);
                } catch (Exception e) {
                    LoggerFactory.getLogger(StationsAPIImpl.class).error(e.getMessage());
                }
            }

            if (stateHistoryDeepth != StateHistoryDeepth.none) {
                List<StationState> stationsStates = getCurrentStates();

                for (int iStation = 0; iStation < stations.size(); iStation++) {
                    for (int iState = 0; iState < stationsStates.size(); iState++) {
                        if (stations.get(iStation).getStationCode() == stationsStates.get(iState).getStation().getStationCode()) {
                            stations.get(iStation).addState(stationsStates.get(iState));
                        }
                    }
                }
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return stations;
    }


    public List<StationState> getCurrentStates() {
        String response = Api.callAPI("https://velib-metropole-opendata.smoove.pro/opendata/Velib_Metropole/station_status.json");

        ArrayList<StationState> listStationStates = new ArrayList<StationState>();

        try {
            JSONObject tomJsonObject = new JSONObject(response);

            JSONArray listStationsJSON = tomJsonObject.getJSONObject("data").getJSONArray("stations");


            for (int i = 0; i < listStationsJSON.length(); i++) {
                try {
                    StationState stationState = new StationState();
                    Station station = new Station();
                    station.setStationCode(listStationsJSON.getJSONObject(i).getLong("stationCode"));

                    stationState.setStation(station);
                    stationState.setIsInstalled(listStationsJSON.getJSONObject(i).getInt("is_installed") == 1);
                    stationState.setIsRenting(listStationsJSON.getJSONObject(i).getInt("is_renting") == 1);
                    stationState.setIsReturning(listStationsJSON.getJSONObject(i).getInt("is_returning") == 1);
                    stationState.setLastReported(listStationsJSON.getJSONObject(i).getInt("last_reported"));
                    stationState.setNumBikesAvailable(listStationsJSON.getJSONObject(i).getInt("num_bikes_available"));
                    stationState.setNumBikesAvailableTypesMechanical(listStationsJSON.getJSONObject(i).getJSONArray("num_bikes_available_types").getJSONObject(0).getInt("mechanical"));
                    stationState.setNumBikesAvailableTypesEbike(listStationsJSON.getJSONObject(i).getJSONArray("num_bikes_available_types").getJSONObject(1).getInt("ebike"));
                    stationState.setNumDocksAvailable(listStationsJSON.getJSONObject(i).getInt("num_docks_available"));
                    stationState.setLastReported(listStationsJSON.getJSONObject(i).getInt("last_reported"));
                    stationState.setTimestampInformationGot(System.currentTimeMillis());

                    listStationStates.add(stationState);
                } catch (Exception e) {
                    LoggerFactory.getLogger(StationsAPIImpl.class).error(e.getMessage());
                }
            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return listStationStates;
    }

}
