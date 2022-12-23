package com.pic.velib.service.impl;

import com.pic.velib.entity.User;
import com.pic.velib.repository.UserRepository;
import com.pic.velib.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    protected UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(String id) {
        return userRepository.findById(id);
    }
}
