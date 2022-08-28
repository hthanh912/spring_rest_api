package com.example.springrest.controllers;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.ArtistDTO;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.entities.Artist;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
  private final ArtistService artistService;

  @Autowired
  public ArtistController(ArtistService artistService) {
    this.artistService = artistService;
  }

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllArtists() {
    List<ArtistDTO> listArtist = this.artistService.getAllArtist();
    return ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject(HttpStatus.OK.value(), "Found " + listArtist.size() + " artist(s)", listArtist));
  }

  @PostMapping(value = "")
  public ResponseEntity<ResponseObject> insertArtist(@RequestBody Artist artist) {
    ArtistDTO insertedArtist = this.artistService.insertArtist(artist);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
        HttpStatus.OK.value(), "Inserted " + insertedArtist.getName(), insertedArtist));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> getArtistById(@PathVariable Long id) {
    ArtistDTO artistDTO = this.artistService.getArtistById(id);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + artistDTO.getName(), artistDTO));
  }

  @GetMapping(value = "/{id}/albums")
  public ResponseEntity<ResponseObject> getAlbumsByArtistId(@PathVariable Long id) {
    List<AlbumDTO> albums = this.artistService.getAlbumByArtistId(id);
    return ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject(HttpStatus.OK.value(), "Found " + albums.size() + " albums", albums));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> deleteArtist(@PathVariable Long id) {
    this.artistService.deleteArtist(id);
    return ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject(HttpStatus.OK.value(), "Deleted artist id " + id));
  }

  @GetMapping("/{id}/songs")
  public ResponseEntity<ResponseObject> getAllSongsByArtist(@PathVariable Long id, Pageable pageable) {
    List<SongDTO> listSong = this.artistService.getSongByArtistId(id, pageable);
    return ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject(HttpStatus.OK.value(),"Foundz " + listSong.size() + "songs", listSong));
  }
}
