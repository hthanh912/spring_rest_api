package com.example.springrest.controllers;

import com.example.springrest.dto.AlbumDTO;

import com.example.springrest.dto.ResponseObject;
import com.example.springrest.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {
  private final AlbumService albumService;

  @Autowired
  public AlbumController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllAlbum() {
    List<AlbumDTO> albumDTOs = this.albumService.getAllAlbum();
    return ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject(200, "Found " + albumDTOs.size() + " artist(s)", albumDTOs));
  };

  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject> getAlbumById(@PathVariable Long id) {
    AlbumDTO albumDTO = this.albumService.findById(id);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject(200, "Found album id " + albumDTO.getId(), albumDTO));
  };

  @PostMapping(value = "")
  public ResponseEntity<ResponseObject> insertAlbum(@RequestBody AlbumDTO albumDTO) {
    AlbumDTO insertedAlbum = this.albumService.insertAlbum(albumDTO);
    return ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject(200, "Inserted album " + insertedAlbum.getTitle(), insertedAlbum));
  };

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> deleteAlbum(@PathVariable Long id) {
    this.albumService.deleteAlbum(id);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject(200, "Deleted album id " + id));
  }

  @GetMapping("/{id}/songs")
  public ResponseEntity<ResponseObject> getAllSongsByAlbums(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject(HttpStatus.OK.value(), "Found "+ " song(s)", this.albumService.getAllSongByAlbumId(id)));
  };
}
