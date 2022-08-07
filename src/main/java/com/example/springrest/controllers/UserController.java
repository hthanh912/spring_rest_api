package com.example.springrest.controllers;

import com.example.springrest.dto.UserDTO;
import com.example.springrest.entities.ResponseObject;
import com.example.springrest.entities.User;
import com.example.springrest.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class UserController {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/user/me")
  public ResponseObject getUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUserName = authentication.getName();
      Optional<User> user = userRepository.findUserByUsername(currentUserName);
      if (user.isPresent()) {
        UserDTO userDto = new UserDTO(user.get());
        System.out.println(userDto);
        return new ResponseObject(200, "get user successful", userDto);
      }
    }
    return new ResponseObject(400, "get user failed", new Object());
  }
}
