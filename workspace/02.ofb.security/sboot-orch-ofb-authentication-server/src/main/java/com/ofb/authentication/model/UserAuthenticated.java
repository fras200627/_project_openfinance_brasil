package com.ofb.authentication.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

public class UserAuthenticated implements UserDetails {
  final RegisteredClientEntity user;

  public UserAuthenticated(RegisteredClientEntity user) {
    this.user = user;
  }
  
  public String getUsernameFull() {
    return user.getClientName();
  }

  @Override
  public String getUsername() {
    return user.getClientId();
  }

  @Override
  public String getPassword() {
    return user.getClientSecret();
  }

  public Timestamp getClientSecretExpiresAt() {
    return user.getClientSecretExpiresAt();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> user.getAuthorizationGrantTypes());
  }

  public String getScopes() {
    return user.getScopes();
  }
  
  @Override
  public boolean isAccountNonExpired() {
    if (user.getClientSecretExpiresAt().toInstant().isBefore(Instant.now()) == true) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean isAccountNonLocked() {
    if (user.getClientSecretExpiresAt().toInstant().isBefore(Instant.now()) == true) {
      return false;
    } else {
      return user.getStatus().toLowerCase().equals("active") ? true : false;
    }
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return user.getClientSecretExpiresAt().toInstant().isBefore(Instant.now()) ? false : true;
  }

  @Override
  public boolean isEnabled() {
    if (user.getClientSecretExpiresAt().toInstant().isBefore(Instant.now()) == true) {
      return false;
    } else {
      return true;
    }
  }

}
