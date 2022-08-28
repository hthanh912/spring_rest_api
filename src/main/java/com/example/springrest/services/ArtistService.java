package com.example.springrest.services;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.ArtistDTO;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Artist;
import exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
  List<ArtistDTO> getAllArtist() throws ResourceNotFoundException;
  ArtistDTO getArtistById(Long id) throws ResourceNotFoundException;
  List<SongDTO> getSongByArtistId(Long id, Pageable pageable) throws ResourceNotFoundException;
  List<AlbumDTO> getAlbumByArtistId(Long artisId) throws ResourceNotFoundException;
  ArtistDTO insertArtist(Artist artist);

  void deleteArtist(Long id);
}
