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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
  @Autowired
  ArtistRepository artistRepository;

  @Autowired
  AlbumRepository albumRepository;

  private final ArtistService artistService;

  @Autowired
  public ArtistController(ArtistService artistService) {
    this.artistService = artistService;
  }

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllArtists() {
    List<ArtistDTO> listArtist = this.artistService.getAllArtist();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.value(), "Found " + listArtist.size() + " artist(s)", listArtist));
  }

  @PostMapping(value = "")
  public ResponseEntity<ResponseObject> insertArtist(@RequestBody Artist artist) {
    ArtistDTO insertedArtist = this.artistService.insertArtist(artist);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Inserted " + insertedArtist.getName(), insertedArtist));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> getArtistById(@PathVariable Long id) {
    ArtistDTO artistDTO = this.artistService.getArtistById(id);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + artistDTO.getName(), artistDTO));
  }

  // TODO
  @GetMapping(value = "/{id}/albums")
  public ResponseEntity<ResponseObject> getAlbumsByArtistId(@PathVariable Long id) {
    Optional<List<Album>> albums = Optional.ofNullable(albumRepository.findAllByArtistId(id));
    if (albums.isPresent()) {
      List<AlbumDTO> albumDTOs = new ArrayList<AlbumDTO>();
      albums.get().forEach(album -> albumDTOs.add(new AlbumDTO(album)));
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + albumDTOs.size() + " albums", albumDTOs));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", null));
  }

  // TODO
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> deleteArtist(@PathVariable Long id) {
    Optional<Artist> artist = artistRepository.findById(id);
    if (artist.isPresent()) {
      artistRepository.deleteById(artist.get().getId());
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Deleted artist id " + id, null));
    } else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(200, "Not found artist id " + id, null));
  }

  @GetMapping("/{id}/songs")
  public ResponseEntity<ResponseObject> getAllSongsByArtist(@PathVariable Long id) {
    List<SongDTO> listSong = this.artistService.getSongByArtistId(id);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Found ", listSong));
  }

}
