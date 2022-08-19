package com.example.springrest.services;

import com.example.springrest.dto.ResponseObject;
import com.example.springrest.dto.SongDTO;
import exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface SongService {
  SongDTO findById(Long id) throws ResourceNotFoundException;
  List<SongDTO> getAllSong(Pageable pageable);
  List<SongDTO> getSongsByAlbumId(Long albumId);
  SongDTO insertSong(String title, Long artistId, Long albumId) throws ResourceNotFoundException;
  Void deleteSong(Long id) throws ResourceNotFoundException;
}