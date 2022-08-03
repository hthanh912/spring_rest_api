package com.example.springrest.controllers;

import com.example.springrest.entities.Artist;
import com.example.springrest.entities.ResponseObject;
import com.example.springrest.respositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
  @Autowired
  ArtistRepository artistRepository;

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllArtists() {
    Optional<List<Artist>> artists = Optional.of(artistRepository.findAll());
    if (artists.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + artists.get().size() + " artist(s)", artists.get()));
    };
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", new Array[0]));
  };
//
//  @PostMapping("")
//  public ResponseEntity<ResponseObject> insertSong(@RequestParam(name = "asd")String asd) {
//    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Inserted ", new Object()));
//  };
}
