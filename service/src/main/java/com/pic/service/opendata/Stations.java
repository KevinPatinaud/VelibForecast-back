package com.pic.service.opendata;

import com.pic.service.api.Api;
import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Stations {

    private static final Logger log = LoggerFactory.getLogger(StationStates.class);

    public enum StateHistoryDeepth {none, current, all}



    public static List<Station> getStations() {
        return getStations(StateHistoryDeepth.none);
    }

    public static List<Station> getStations(StateHistoryDeepth stateHistoryDeepth) {
        String responseAPI = Api.callAPI("https://velib-metropole-opendata.smoove.pro/opendata/Velib_Metropole/station_information.json");
        List<Station> stations = new ArrayList<Station>();


        try {
            JSONObject tomJsonObject = new JSONObject(responseAPI);

            JSONArray listStationsJSON = tomJsonObject.getJSONObject("data").getJSONArray("stations");
            listStationsJSON.getJSONObject(0).getString("stationCode");


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
                    log.error(e.getMessage());
                }
            }

            if (stateHistoryDeepth != StateHistoryDeepth.none) {
                List<StationState> stationsStates = StationStates.getCurrentStates();

                for (int iStation = 0; iStation < stations.size(); iStation++) {
                    for (int iState = 0; iState < stationsStates.size(); iState++) {
                        if (stations.get(iStation).getStationCode() == stationsStates.get(iState).getStationCode()) {
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
}
