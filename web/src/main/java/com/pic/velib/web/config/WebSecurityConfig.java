package com.pic.velib.web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                .antMatchers("/api/station/*").permitAll()
                .antMatchers( "/api/user/ismailalreadyrecorded").permitAll()
                .antMatchers( HttpMethod.POST,"/api/user/facebookuser").permitAll()
                .antMatchers( HttpMethod.PUT,"/api/user/facebookuser").permitAll()
                .antMatchers( "/api/user/addfavoritestation").permitAll()
                .antMatchers( "/api/user/removefavoritestation").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/mailuser").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/user/mailuser").hasRole("USER").and().httpBasic();

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

}
