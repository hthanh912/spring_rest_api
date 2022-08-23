package com.example.springrest.services;

import com.example.springrest.dto.ArtistDTO;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Artist;
import exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
  List<ArtistDTO> getAllArtist() throws ResourceNotFoundException;
  ArtistDTO getArtistById(Long id) throws ResourceNotFoundException;
  List<SongDTO> getSongByArtistId(Long id) throws ResourceNotFoundException;
  ArtistDTO insertArtist(Artist artist);
}
