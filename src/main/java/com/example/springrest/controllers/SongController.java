package com.example.springrest.controllers;

import com.example.springrest.entities.Song;
import com.example.springrest.respositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
  @Autowired SongRepository songRepository;

  @GetMapping("")
  public ResponseEntity<List<Song>> getAllSongs() {
    try {
      List<Song> songs = new ArrayList<Song>();
      songRepository.findAll().forEach(songs::add);
      return new ResponseEntity<>(songs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
