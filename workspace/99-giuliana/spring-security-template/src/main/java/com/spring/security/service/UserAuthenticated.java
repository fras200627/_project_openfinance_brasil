package com.spring.security.service;

import java.util.Collection;
import java.util.List;

import com.spring.security.model.RegisteredClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthenticated implements UserDetails {
  private final RegisteredClient user;

  public UserAuthenticated(RegisteredClient user) {
    this.user = user;
  }

  @Override
  public String getUsername() {
    return user.getClientId();
  }

  @Override
  public String getPassword() {
    return user.getClientSecret();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> "read");
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
