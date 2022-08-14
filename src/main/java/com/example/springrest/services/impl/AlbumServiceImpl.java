package com.example.springrest.services.impl;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.services.AlbumService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

  private final AlbumRepository albumRepository;

  @Autowired
  public AlbumServiceImpl(AlbumRepository albumRepository) {
    this.albumRepository = albumRepository;
  }

  @Override
  public AlbumDTO findById(Long id) throws ResourceNotFoundException {
    Album album = albumRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found album id " + id));
    return new AlbumDTO(album);
  }
}
