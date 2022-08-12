package com.example.springrest.services;

import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface SongService {
  ResponseEntity<ResponseObject> findById(Long id);
}