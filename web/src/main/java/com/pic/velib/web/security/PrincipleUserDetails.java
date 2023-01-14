package com.pic.velib.web.security;

import com.pic.velib.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipleUserDetails implements UserDetails {
    private User user;

    private ArrayList<String> authorities = new ArrayList<>();

    public PrincipleUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] autho = new String [authorities.size()];

        for (int i = 0 ; i < authorities.size() ; i++)
        {
            autho[i] = authorities.get(i);
        }

        return AuthorityUtils.createAuthorityList(autho );
    }

    public void addAuthorities(String authority)
    {
        authorities.add(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}