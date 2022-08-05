package com.example.springrest.controllers;

import com.example.springrest.entities.Artist;
import com.example.springrest.entities.ResponseObject;
import com.example.springrest.respositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  @PostMapping(value = "")
  public ResponseEntity<ResponseObject> insertSong(@RequestBody Artist artist) {
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Inserted " + artist.getName(), artistRepository.save(artist)));
  };

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> deleteSong(@PathVariable Long id) {
    Optional<Artist> artist = artistRepository.findById(id);
    if (artist.isPresent()) {
      artistRepository.deleteById(artist.get().getId());
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Deleted artist id " + id, null ));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(200, "Not found artist id " + id, null ));
  }
}
