package com.pic.velib.entity;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class StationState {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stationCode", nullable=false)
    private Station station;

    private long timeStampInformationGot;
    private int numBikesAvailable;
    private int numBikesAvailableTypesMechanical;
    private int numBikesAvailableTypesEbike;
    private int numDocksAvailable;
    private boolean isInstalled;
    private boolean isReturning;
    private boolean isRenting;
    private int lastReported;


    public Long getId() {
        return id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getNumBikesAvailable() {
        return numBikesAvailable;
    }

    public void setNumBikesAvailable(int numBikesAvailable) {
        this.numBikesAvailable = numBikesAvailable;
    }


    public int getNumBikesAvailableTypesMechanical() {
        return numBikesAvailableTypesMechanical;
    }

    public void setNumBikesAvailableTypesMechanical(int numBikesAvailableTypesMechanical) {
        this.numBikesAvailableTypesMechanical = numBikesAvailableTypesMechanical;
    }

    public int getNumBikesAvailableTypesEbike() {
        return numBikesAvailableTypesEbike;
    }

    public void setNumBikesAvailableTypesEbike(int numBikesAvailableTypesEbike) {
        this.numBikesAvailableTypesEbike = numBikesAvailableTypesEbike;
    }

    public int getNumDocksAvailable() {
        return numDocksAvailable;
    }

    public void setNumDocksAvailable(int numDocksAvailable) {
        this.numDocksAvailable = numDocksAvailable;
    }


    public boolean getIsInstalled() {
        return isInstalled;
    }

    public void setIsInstalled(boolean isInstalled) {
        this.isInstalled = isInstalled;
    }

    public boolean getIsReturning() {
        return isReturning;
    }

    public void setIsReturning(boolean isReturning) {
        this.isReturning = isReturning;
    }

    public boolean getIsRenting() {
        return isRenting;
    }

    public void setIsRenting(boolean isRenting) {
        this.isRenting = isRenting;
    }

    public int getLastReported() {
        return lastReported;
    }

    public void setLastReported(int lastReported) {
        this.lastReported = lastReported;
    }

    public boolean isEqual(StationState stationState)
    {
        return stationState != null
                && this.isReturning == stationState.isReturning
                && this.station.getStationCode() == stationState.getStation().getStationCode()
                && this.isInstalled == stationState.getIsInstalled()
                && this.isRenting == stationState.getIsRenting()
                && this.numBikesAvailable == stationState.getNumBikesAvailable()
                && this.numBikesAvailableTypesEbike == stationState.getNumBikesAvailableTypesEbike()
                && this.numBikesAvailableTypesMechanical == stationState.getNumBikesAvailableTypesMechanical()
                && this.numDocksAvailable == stationState.getNumDocksAvailable();

    }

    public long getTimeStampInformationGot() {
        return timeStampInformationGot;
    }

    public void setTimeStampInformationGot(long timeStampInformationGot) {
        this.timeStampInformationGot = timeStampInformationGot;
    }
}
