package com.example.springrest.services;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.SongDTO;
import exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlbumService {
  List<AlbumDTO> getAllAlbum() throws ResourceNotFoundException;
  AlbumDTO findById(Long id) throws ResourceNotFoundException;
  List<SongDTO> getAllSongByAlbumId(Long id) throws ResourceNotFoundException;
  AlbumDTO insertAlbum(String title, String description, Long albumId);
  void deleteAlbum(Long id) throws ResourceNotFoundException;
}
