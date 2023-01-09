package com.pic.velib.repository;

import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StationRepository  extends CrudRepository<Station, Long> {
    Station findByStationCode(long id_station);
}
