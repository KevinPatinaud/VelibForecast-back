package com.pic.velib.web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter{

    private UserDetailsService userDetailsService;

    private PasswordEncoder encoder;

    @Autowired
    WebSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        System.out.println(userDetailsService.getClass());
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .authorizeRequests()
                .antMatchers("/api/station/states").permitAll()
                .antMatchers("/api/station/").hasRole("USER")

                .anyRequest()
                .authenticated().and().httpBasic(); // httpBasic est obligatoire pour utiliser auth Basic (sur PostMan)


        // Permet d'utiliser un provider (UserDetailsService) personnalisé, est optionnelle
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

}
