package com.example.springrest.services;

import com.example.springrest.dto.ArtistDTO;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.entities.Artist;
import exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ArtistService {
  ArtistDTO findById(Long id) throws ResourceNotFoundException;
  List<SongDTO> getSongByArtistId(Long id) throws ResourceNotFoundException;
}
