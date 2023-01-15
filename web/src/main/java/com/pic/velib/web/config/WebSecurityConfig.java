package com.pic.velib.web.config;


import com.pic.velib.web.security.AuthFacebookFilter;
import com.pic.velib.web.security.AuthJWTFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;

    private AuthFacebookFilter authFacebookFilter;

    private AuthJWTFilter authJWTFilter;
    private PasswordEncoder encoder;

    @Autowired
    WebSecurityConfig(UserDetailsService userDetailsService, AuthFacebookFilter authFacebookFilter, AuthJWTFilter authJWTFilter, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.authFacebookFilter = authFacebookFilter;
        this.authJWTFilter = authJWTFilter;
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/station/**").permitAll()
                .antMatchers( "/api/user/ismailalreadyrecorded").permitAll()
                .antMatchers( HttpMethod.POST,"/api/user/facebookuser").permitAll()
                .antMatchers( HttpMethod.PUT,"/api/user/facebookuser").hasRole("USER_FACEBOOK")
                .antMatchers( "/api/user/addfavoritestation").hasRole("USER" )
                .antMatchers( "/api/user/removefavoritestation").hasRole("USER" )
                .antMatchers(HttpMethod.POST, "/api/user/mailuser").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/user/mailuser").hasRole("USER_MAIL").and().httpBasic();

        http.authenticationProvider(authenticationProvider());

       http.addFilterAfter(authFacebookFilter, BasicAuthenticationFilter.class);
       http.addFilterAfter(authJWTFilter,AuthFacebookFilter.class);

        return http.build();
    }

}
