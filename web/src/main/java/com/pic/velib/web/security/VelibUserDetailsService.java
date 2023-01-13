package com.pic.velib.web.security;

import com.pic.velib.entity.User;
import com.pic.velib.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class VelibUserDetailsService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    DataSource datasource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLL");
        System.out.println(username);
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLL");

        User user =   userRepository.findByUsername(username);

        System.out.println(user);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);

    }
}
