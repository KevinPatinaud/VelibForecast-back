package com.pic.velib.impl;

import com.pic.velib.StationService;
import com.pic.velib.entity.StationState;
import com.pic.velib.repository.StationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StationServiceImpl implements StationService {


    StationStateRepository stationStateRepository;


    protected StationServiceImpl(StationStateRepository stationStateRepository) {
        this.stationStateRepository = stationStateRepository;
    }

    @Override
    public List<StationState> findStationStates() {
        Iterable<StationState> iterable = stationStateRepository.findAll();


        List<StationState> result =
                StreamSupport.stream(iterable.spliterator(), false)
                        .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<StationState> findStationStates(long stationCode) {
        return stationStateRepository.findByStationCodeOrderByLastReportedDesc(stationCode);
    }

    @Override
    public void saveStrationState(StationState stationState) {
        stationStateRepository.save(stationState);
    }

    @Override
    public void deleteAllStationStates() {
        stationStateRepository.deleteAll();
    }

    @Override
    public void updateStationState(StationState stationState) {
        List<StationState> stationStates = findStationStates(stationState.getStationCode());
        if (stationStates.size() == 0) // if the station was already exist in the database
        {
            // we add it
            stationStateRepository.save(stationState);
        } else {
            // if the station already exist into the database, we compare each element to know if it's useful to persist modifications
            if (!stationState.isEqualExceptLastReported(stationStates.get(0))) {
                stationStateRepository.save(stationState);
            }
        }
    }


}
