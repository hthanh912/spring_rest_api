package com.example.springrest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class ResponseObject {
  private Integer status;
  private String message;

  private Object data;

  public ResponseObject(Integer status, String message) {
    this.message = message;
    this.status = status;
  }
}
