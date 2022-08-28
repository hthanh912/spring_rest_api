package com.example.springrest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorObject {
  private Integer status;
  private String message;
  public ErrorObject(Integer status, String message) {
    this.status = status;
    this.message = message;
  }
}
