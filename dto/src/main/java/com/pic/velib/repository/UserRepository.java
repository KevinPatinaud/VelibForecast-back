package com.pic.velib.repository;

import com.pic.velib.entity.Station;
import com.pic.velib.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, String> {
}
