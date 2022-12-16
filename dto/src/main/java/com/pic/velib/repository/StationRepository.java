package com.pic.velib.repository;

import com.pic.velib.entity.Station;
import com.pic.velib.entity.StationState;
import org.springframework.data.repository.CrudRepository;

public interface StationRepository  extends CrudRepository<Station, Long> {
}
