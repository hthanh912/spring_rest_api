package com.example.springrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/home")
  public String home(){
    System.out.println("home");
    return new String("asdhas");
  }
}
