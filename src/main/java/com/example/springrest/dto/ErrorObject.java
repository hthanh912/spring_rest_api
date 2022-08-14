package com.example.springrest.dto;

public class ErrorObject {
  private Integer status;

  private String message;

  public ErrorObject(Integer status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
