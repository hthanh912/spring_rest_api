package com.example.springrest.controllers;

import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.ResponseObject;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {
  @Autowired SongRepository songRepository;

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllSongs() {
    Optional<List<Song>> songs = Optional.ofNullable(songRepository.findAll());
    if (songs.isPresent()) {
      List<SongDTO> songsDto = new ArrayList<SongDTO>();
      songs.get().forEach(song -> songsDto.add(new SongDTO(song)));
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + songsDto.size() + " song(s)", songsDto));
    };
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", new Array[0]));
  };

  @PostMapping("")
  public ResponseEntity<ResponseObject> insertSong(@RequestParam(value = "title")String title, @RequestParam(value = "artist")String artist, @RequestParam(value = "album")String album) {
    return null;
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(200, "Inserted " + newSong.toString(), songRepository.save(newSong)));
  };
}
