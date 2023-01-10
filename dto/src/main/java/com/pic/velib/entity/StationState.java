package com.pic.velib.entity;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class StationState {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_code", nullable=false)
    private Station station;

    @Column(name="timestamp_information_got")
    private long timestampInformationGot;

    @Column(name="num_bikes_available")
    private int numBikesAvailable;
    @Column(name="num_bikes_available_types_mechanical")
    private int numBikesAvailableTypesMechanical;
    @Column(name="num_bikes_available_types_ebike")
    private int numBikesAvailableTypesEbike;
    @Column(name="num_docks_available")
    private int numDocksAvailable;
    @Column(name="is_installed")
    private boolean isInstalled;
    @Column(name="is_returning")
    private boolean isReturning;
    @Column(name="is_renting")
    private boolean isRenting;
    @Column(name="last_reported")
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

    public long getTimestampInformationGot() {
        return timestampInformationGot;
    }

    public void setTimestampInformationGot(long timeStampInformationGot) {
        this.timestampInformationGot = timeStampInformationGot;
    }
}
