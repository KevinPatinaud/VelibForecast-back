package com.pic.velib.web.security;

import com.pic.velib.entity.User;
import com.pic.velib.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collection;


@Service
public class VelibUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DataSource datasource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =   userRepository.findByUsername(username);


        if (user == null) {
            return null;
        }
        return new VelibUserDetails(user);

    }
}
