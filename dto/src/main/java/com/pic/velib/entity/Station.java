package com.pic.velib.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @Column(name = "station_code")
    private long stationCode;

    @OneToMany(mappedBy = "station")
    private List<StationState> states;

    @Column(name = "timestamp_information_got")
    private long timeStampInformationGot;
    private String name;

    private double latitude;

    private double longitude;

    private int capacity;

    @Column(name = "rental_methods")
    private String rentalMethods;


    public long getStationCode() {
        return stationCode;
    }

    public void setStationCode(long stationCode) {
        this.stationCode = stationCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public String getRentalMethods() {
        return rentalMethods;
    }

    public void setRentalMethods(String rentalMethods) {
        this.rentalMethods = rentalMethods;
    }

    public List<StationState> getStates() {
        return states;
    }

    public void setStates(List<StationState> states) {
        this.states = states;
    }

    public void addState(StationState state) {
        if (this.states == null) this.states = new ArrayList<StationState>();
        this.states.add(state);
    }

    public long getTimeStampInformationGot() {
        return timeStampInformationGot;
    }

    public void setTimeStampInformationGot(long timeStampInformationGot) {
        this.timeStampInformationGot = timeStampInformationGot;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        try {
            json.put("stationCode", this.stationCode);
            json.put("timeStampInformationGot", this.timeStampInformationGot);
            json.put("name", this.name);
            json.put("latitude", this.latitude);
            json.put("longitude", this.longitude);
            json.put("capacity", this.capacity);
            json.put("rentalMethods", this.rentalMethods);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return json;
    }
}
