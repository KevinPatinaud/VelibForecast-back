package com.pic.velib.service.opendata;

import com.pic.velib.service.api.Api;
import com.pic.velib.entity.StationState;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

public class StationStates {


    private static final Logger log = LoggerFactory.getLogger(StationStates.class);

    public static List<StationState> getCurrentStates() {
        String response = Api.callAPI("https://velib-metropole-opendata.smoove.pro/opendata/Velib_Metropole/station_status.json");

        ArrayList<StationState> listStationStates = new ArrayList<StationState>();

        try {
            JSONObject tomJsonObject = new JSONObject(response);

            JSONArray listStationsJSON = tomJsonObject.getJSONObject("data").getJSONArray("stations");
            listStationsJSON.getJSONObject(0).getString("stationCode");


            for (int i = 0; i < listStationsJSON.length(); i++) {
                try {
                    StationState stationState = new StationState();

                    stationState.setStationCode(listStationsJSON.getJSONObject(i).getLong("stationCode"));
                    stationState.setIsInstalled(listStationsJSON.getJSONObject(i).getInt("is_installed") == 1);
                    stationState.setIsRenting(listStationsJSON.getJSONObject(i).getInt("is_renting") == 1);
                    stationState.setIsReturning(listStationsJSON.getJSONObject(i).getInt("is_returning") == 1);
                    stationState.setLastReported(listStationsJSON.getJSONObject(i).getInt("last_reported"));
                    stationState.setNumBikesAvailable(listStationsJSON.getJSONObject(i).getInt("num_bikes_available"));
                    stationState.setNumBikesAvailableTypesMechanical(listStationsJSON.getJSONObject(i).getJSONArray("num_bikes_available_types").getJSONObject(0).getInt("mechanical"));
                    stationState.setNumBikesAvailableTypesEbike(listStationsJSON.getJSONObject(i).getJSONArray("num_bikes_available_types").getJSONObject(1).getInt("ebike"));
                    stationState.setNumDocksAvailable(listStationsJSON.getJSONObject(i).getInt("num_docks_available"));
                    stationState.setLastReported(listStationsJSON.getJSONObject(i).getInt("last_reported"));
                    stationState.setTimeStampInformationGot(System.currentTimeMillis());

                    listStationStates.add(stationState);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return listStationStates;
    }


}
