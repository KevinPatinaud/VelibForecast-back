package com.pic.velib.web.config;


import com.pic.velib.web.security.VelibUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter{

    //////////////////////////////////////////////////////////////////////////////////////////////
   // voir : https://github.com/alexeymatveev12/task_6.Spring-security/blob/5f9f4577fb06954b97211779f68d18bda536e1d6/src/main/java/task5/spring/config/SpringSecurityConfig.java
    //////////////////////////////////////////////////////////////////////////////////////




    private UserDetailsService userDetailsService;

    private PasswordEncoder encoder;

    @Autowired
    WebSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        System.out.println(userDetailsService.getClass());
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

/*

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVV");
        System.out.println(userDetailsService.getClass());
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMM");
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);


    }
*/

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        System.out.println("authenticationProvider : " + userDetailsService.getClass());

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/station/states").permitAll()
                .antMatchers("/api/station/").hasRole("USER")

                .anyRequest()
                .authenticated().and().httpBasic();


        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
/*
    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/station/states").permitAll()
                .antMatchers("/api/station/").hasRole("USER")

                .anyRequest().authenticated();


        http.authenticationProvider(authenticationProvider());

        return http.build();
    }


/*

    @Override
    protected void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception
    {
        System.out.println("HHHHHHHHHHHHHHHHHHHHH");

        auth.userDetailsService(new VelibUserDetailsService());

/*
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .getUserDetailsService();


    }
    */


}
