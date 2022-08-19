package com.example.springrest.services.impl;

import com.example.springrest.dto.ArtistDTO;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.entities.Artist;
import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.services.ArtistService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;


@Service
public class ArtistServiceImpl implements ArtistService {

  private final ArtistRepository artistRepository;
  private final SongService songService;

  @Autowired
  public ArtistServiceImpl(ArtistRepository artistRepository, @Lazy SongService songService) {
    this.artistRepository = artistRepository;
  }

  @Override
  public ArtistDTO findById(Long id) throws ResourceNotFoundException {
    Artist artist = this.artistRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found artist id " + id));
    return new ArtistDTO(artist);
  }

  @Override
  public List<SongDTO> getSongByArtistId(Long id) throws ResourceNotFoundException {
    return this.songService.getSongsByArtistId(id);
  }
}
