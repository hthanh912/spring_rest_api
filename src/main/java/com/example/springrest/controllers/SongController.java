package com.example.springrest.controllers;

import com.example.springrest.dto.SongDTO;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
  private final SongService songService;
  @Autowired
  public SongController(SongService songService) {
    this.songService = songService;
  }

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllSongs(Pageable pageable) {
    List<SongDTO> songDTOS = this.songService.getAllSong(pageable);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject(HttpStatus.OK.value(), "Found " + songDTOS.size() + " song(s)",songDTOS));
  }

  @PostMapping("")
  public ResponseEntity<ResponseObject> insertSong(@RequestBody SongDTO songDTO) {
    SongDTO insertedSong = this.songService.insertSong(songDTO);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject(HttpStatus.OK.value(), "inserted " + insertedSong.getTitle(), insertedSong));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject> getSongById(@PathVariable Long id) {
    SongDTO songDTO = this.songService.findById(id);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject(HttpStatus.OK.value(),"Found song id " + songDTO.getId(), songDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseObject> deleteSong(@PathVariable Long id) {
    this.songService.deleteSong(id);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Deleted song id " + id));
  }
}
