package com.pic.velib.repository;

import com.pic.velib.entity.StationState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationStateRepository extends CrudRepository<StationState, Long>  {

    List<StationState> findByStationCodeOrderByTimeStampInformationGotDesc(long stationCode);
    StationState findFirstByStationCodeOrderByTimeStampInformationGotDesc(long stationCode);
    List<StationState> findByStationCode(long stationCode);

}
