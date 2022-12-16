package com.pic.velib.service.impl;

import com.pic.velib.service.StationService;
import com.pic.velib.entity.StationState;
import com.pic.velib.repository.StationStateRepository;
import org.springframework.stereotype.Service;

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
        return stationStateRepository.findByStationCodeOrderByTimeStampInformationGotDesc(stationCode);

    }

    @Override
    public StationState findLastStationStates(long stationCode) {
        return stationStateRepository.findFirstByStationCodeOrderByTimeStampInformationGotDesc(stationCode);

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
        StationState lastSavedStationState = findLastStationStates(stationState.getStationCode());

        if (!stationState.isEqual(lastSavedStationState)) {
            stationStateRepository.save(stationState);
        }
    }
}
