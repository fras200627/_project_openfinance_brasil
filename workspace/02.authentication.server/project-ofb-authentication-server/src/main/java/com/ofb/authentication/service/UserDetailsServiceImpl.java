package com.ofb.authentication.service;

import com.ofb.authentication.model.UserAuthenticated;
import com.ofb.authentication.repository.RegisteredClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
  private final RegisteredClientRepository userRepository;

  public UserDetailsServiceImpl(RegisteredClientRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByClientId(username)
        .map(user -> new UserAuthenticated(user))
        .orElseThrow(
            () -> new UsernameNotFoundException("User Not Found with username: " + username));
  }

  public UserAuthenticated loadUserAuthenticated(String username) throws UsernameNotFoundException {
    return new UserAuthenticated(userRepository.findByClientId(username).get());
  }
  
}
