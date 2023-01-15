package com.pic.velib.web.security;

import com.pic.velib.entity.User;
import com.pic.velib.service.dto.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class AuthJWTFilter extends OncePerRequestFilter {

    private UserService userService;

    private JWTUtils jwtUtils;

    @Autowired
    public AuthJWTFilter(UserService userService, JWTUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = parseJwt(request);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

            try {

                User user = userService.getUserById(UUID.fromString(jwtUtils.getPayload(jwt).getString("iduser")));


                PrincipleUserDetails principleUser = new PrincipleUserDetails(user);
                principleUser.addAuthorities("ROLE_USER");


                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principleUser, null, principleUser.getAuthorities());
                authentication.setDetails(principleUser);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        }

        filterChain.doFilter(request, response);
    }


    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}


