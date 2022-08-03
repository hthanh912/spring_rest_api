package com.example.springrest.services;

import com.example.springrest.entities.CustomUserDetails;
import com.example.springrest.entities.User;
import com.example.springrest.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByUsername(username);

    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return new CustomUserDetails(user.get());
  }

}