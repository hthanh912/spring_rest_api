package com.example.springrest.services.impl;

import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.ResponseObject;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.SongRepository;
import com.example.springrest.services.SongService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SongServiceImpl  implements SongService {

  private final SongRepository songRepository;

  @Autowired
  public SongServiceImpl(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  @Override
  public ResponseEntity<ResponseObject> findById(Long id) {
    Song song = this.songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song not found"));
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.value(),"Found song id " + song.getId(), new SongDTO(song)));
  }
}
