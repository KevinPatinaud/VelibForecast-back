package com.pic.velib.repository;

import com.pic.velib.entity.UserFacebook;
import org.springframework.data.repository.CrudRepository;

public interface UserFacebookRepository extends CrudRepository<UserFacebook, Integer> {
    UserFacebook findByFacebookId(String facebookId);
}
