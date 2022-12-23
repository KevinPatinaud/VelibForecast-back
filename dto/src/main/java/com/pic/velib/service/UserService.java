package com.pic.velib.service;

import com.pic.velib.entity.User;

import java.util.Optional;

public interface UserService {
    public void saveUser(User user);
    public Optional<User> findUser(String id);
}
