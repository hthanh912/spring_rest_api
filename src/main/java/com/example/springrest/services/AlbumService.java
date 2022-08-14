package com.example.springrest.services;

import com.example.springrest.dto.AlbumDTO;
import exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AlbumService {
  AlbumDTO findById(Long id) throws ResourceNotFoundException;
}
