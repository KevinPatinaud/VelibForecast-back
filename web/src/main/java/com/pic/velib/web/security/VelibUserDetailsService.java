package com.pic.velib.web.security;

import com.pic.velib.entity.User;
import com.pic.velib.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Service
public class VelibUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =   userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        PrincipleUserDetails ppUser = new PrincipleUserDetails(user);
        ppUser.addAuthorities("ROLE_USER_MAIL");

        return ppUser;

    }
}
