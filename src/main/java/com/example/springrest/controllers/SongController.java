package com.example.springrest.controllers;

import com.example.springrest.entities.ResponseObject;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
  @Autowired SongRepository songRepository;

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllSongs() {
    List<Song> songs = songRepository.findAll();
    return songs.size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + songs.size() + " song(s)", songs))
    : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", new Array[0]));
  }
}
