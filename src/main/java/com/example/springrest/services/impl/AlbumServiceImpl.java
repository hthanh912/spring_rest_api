package com.example.springrest.services.impl;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.services.AlbumService;
import com.example.springrest.services.SongService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

  private final AlbumRepository albumRepository;
  private final SongService songService;

  @Autowired
  public AlbumServiceImpl(AlbumRepository albumRepository,@Lazy SongService songService) {
    this.albumRepository = albumRepository;
    this.songService = songService;
  }

  @Override
  public List<AlbumDTO> findAll() throws ResourceNotFoundException {
    return albumRepository.findAll().stream().map(album -> new AlbumDTO(album)).toList();
  }

  @Override
  public AlbumDTO findById(Long id) throws ResourceNotFoundException {
    Album album = albumRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found album id " + id));
    return new AlbumDTO(album);
  }

  @Override
  public List<SongDTO> getAllSongByAlbumId(Long albumId) throws ResourceNotFoundException {
    return this.songService.getSongsByAlbumId(albumId);
  }
}
