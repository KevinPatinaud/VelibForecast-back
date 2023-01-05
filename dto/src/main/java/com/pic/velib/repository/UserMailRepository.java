package com.pic.velib.repository;

import com.pic.velib.entity.UserMail;
import org.springframework.data.repository.CrudRepository;

public interface UserMailRepository extends CrudRepository<UserMail, Integer> {
    UserMail findByMail(String mail);
}
