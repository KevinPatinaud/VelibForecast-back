package com.pic.velib.service.dto;

import com.pic.velib.entity.Station;
import com.pic.velib.repository.StationRepository;
import com.pic.velib.service.dto.StationService;
import com.pic.velib.entity.StationState;
import com.pic.velib.repository.StationStateRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StationServiceImpl implements StationService {


    private final StationStateRepository stationStateRepository;
    private final StationRepository stationRepository;


    private final EntityManager entityManager;


    protected StationServiceImpl(StationStateRepository stationStateRepository, StationRepository stationRepository, EntityManager entityManager) {
        this.stationStateRepository = stationStateRepository;
        this.stationRepository = stationRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<StationState> findStationStates() {
        Iterable<StationState> iterable = stationStateRepository.findAll();


        List<StationState> result = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<StationState> findStationStates(long stationCode) {
        return null; //stationStateRepository.findByStationCodeOrderByTimeStampInformationGotDesc(stationCode);

    }

    @Override
    public StationState findLastStationStates(long stationCode) {


        try {
            List<StationState> res = entityManager.createNativeQuery("SELECT * FROM StationState WHERE station_code = :idStationCode order by timestamp_information_got DESC LIMIT 1", StationState.class).setParameter("idStationCode", stationCode).getResultList();

            return res.size() > 0 ? res.get(0) : null;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void saveStation(Station station) {
        stationRepository.save(station);
    }

    @Override
    public void saveAllStation(List<Station> stations) {
        stationRepository.saveAll(stations);
    }

    @Override
    public void saveStationState(StationState stationState) {
        Station station = stationRepository.findById(stationState.getStation().getStationCode()).orElse(null);
        stationState.setStation(station);

        stationStateRepository.save(stationState);
    }

    @Override
    public void deleteAllStationStates() {
        stationStateRepository.deleteAll();
    }

    @Override
    public void updateStationState(StationState stationState) {

        StationState lastSavedStationState = findLastStationStates(stationState.getStation().getStationCode()); //stationState.getStationCode());

        if (!stationState.isEqual(lastSavedStationState)) {
            stationStateRepository.save(stationState);
        }
    }

    @Override
    public JSONObject getStationState(long stationCode, int inMinutes) throws Exception {
        Station station = stationRepository.findByStationCode(stationCode);

        if (station == null) throw new Exception();

        JSONObject jsonState = new JSONObject();
        jsonState.put("capacity", station.getCapacity());


        if (inMinutes == 0) {
            StationState state = findLastStationStates(stationCode);

            jsonState.put("numBikesAvailable", state.getNumBikesAvailable());
            jsonState.put("numDockAvailable", station.getCapacity() - state.getNumBikesAvailable());

            return jsonState;
        }

        // !!!!!!!!!!!!!!!!!!!!!!!! the AI is currently in development !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        StationState state = findLastStationStates(stationCode);

        jsonState.put("numBikesAvailable", Math.min( station.getCapacity() , state.getNumBikesAvailable() +  inMinutes / 60));
        jsonState.put("numDockAvailable", Math.max(0, station.getCapacity() - (state.getNumBikesAvailable() + inMinutes / 60)));

        // !!!!!!!!!!!!!!!!!!!!!!!! the AI is currently in development !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        return jsonState;
    }
}
