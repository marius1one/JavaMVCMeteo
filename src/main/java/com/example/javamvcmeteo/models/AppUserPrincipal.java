package com.example.javamvcmeteo.models;

import com.example.javamvcmeteo.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class AppUserPrincipal implements UserDetails {
    private Optional<UserEntity> user;
    public AppUserPrincipal(Optional<UserEntity> user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.get().getRole());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return user.get().password;
    }

    @Override
    public String getUsername() {
        return user.get().username;
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
