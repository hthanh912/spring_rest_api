package com.example.springrest.dto;

public class ResponseObject {
  private String message;
  private Integer status;
  private Object data;

  public ResponseObject(Integer status, String message, Object data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public ResponseObject(Integer status, String message) {
    this.message = message;
    this.status = status;
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

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
