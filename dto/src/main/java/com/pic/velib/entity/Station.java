package com.pic.velib.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station {

    @Id
    private long stationCode;

    @OneToMany(mappedBy="station")
    private List<StationState> states;

    private long timeStampInformationGot;
    private String name;

    private double latitude;

    private double longitude;

    private int capacity;

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
        if (this.states == null)
            this.states = new ArrayList<StationState>();
        this.states.add(state);
    }

    public long getTimeStampInformationGot() {
        return timeStampInformationGot;
    }

    public void setTimeStampInformationGot(long timeStampInformationGot) {
        this.timeStampInformationGot = timeStampInformationGot;
    }
}
