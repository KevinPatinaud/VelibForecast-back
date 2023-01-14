package com.pic.velib.web.security;

import com.pic.velib.entity.User;
import com.pic.velib.repository.UserRepository;
import com.pic.velib.service.dto.UserService;
import com.pic.velib.service.dto.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthFacebookFilter extends OncePerRequestFilter {


    UserService userService;

    @Autowired
    public AuthFacebookFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String facebook_access_token = request.getHeader("facebook_access_token");

        
        System.out.println(facebook_access_token);

        try {
            User user = userService.getUserFacebook(facebook_access_token);

            if (user == null)
                return;

            PrincipleUserDetails principleUser = new PrincipleUserDetails(user);
            principleUser.addAuthorities("ROLE_USER_FACEBOOK");


            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principleUser, null, principleUser.getAuthorities());
            authentication.setDetails(principleUser);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response); // permet de continuer la chaine de filtrage
    }
}
