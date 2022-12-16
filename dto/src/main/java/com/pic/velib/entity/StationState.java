package com.pic.velib.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StationState {

    @Id
    @GeneratedValue
    private long id;

    private long stationCode;
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

    public Long getStationCode() {
        return stationCode;
    }

    public void setStationCode(long stationCode) {
        this.stationCode = stationCode;
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

    public boolean isEqualExceptLastReported(StationState stationState)
    {
        return (this.isReturning == stationState.isReturning
                && this.stationCode == stationState.getStationCode()
                && this.isInstalled == stationState.getIsInstalled()
                && this.isRenting == stationState.getIsRenting()
                && this.numBikesAvailable == stationState.getNumBikesAvailable()
                && this.numBikesAvailableTypesEbike == stationState.getNumBikesAvailableTypesEbike()
                && this.numBikesAvailableTypesMechanical == stationState.getNumBikesAvailableTypesMechanical()
                && this.numDocksAvailable == stationState.getNumDocksAvailable());

    }

}
