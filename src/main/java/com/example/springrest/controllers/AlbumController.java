package com.example.springrest.controllers;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.entities.Artist;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.AlbumRepository;

import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.respositories.SongRepository;
import com.example.springrest.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {
  @Autowired
  AlbumRepository albumRepository;
  @Autowired
  ArtistRepository artistRepository;
  @Autowired
  SongRepository songRepository;

  private final AlbumService albumService;

  @Autowired
  public AlbumController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllAlbum() {
    Optional<List<Album>> albums = Optional.of(albumRepository.findAll());
    if (albums.isPresent()) {
      List<AlbumDTO> albumDTOs = new ArrayList<>();
      albums.get().forEach(album -> albumDTOs.add(new AlbumDTO(album)));
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + albums.get().size() + " artist(s)", albumDTOs));
    };
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", new Array[0]));
  };

  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject> getAlbumById(@PathVariable Long id) {
    AlbumDTO albumDTO = this.albumService.findById(id);
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ResponseObject(200, "Found album id " + albumDTO.getId(), albumDTO));
//    Optional<Album> album = albumRepository.findById(id);
//    if (album.isPresent()) {
//      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found album id " + album.get().getId(), new AlbumDTO(album.get())));
//    };
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", null));
  };

  @PostMapping(value = "")
  public ResponseEntity<ResponseObject> insertAlbum(@RequestBody Map<String, String> params) {
    Optional<String> title = Optional.of(params.get("title"));
    Optional<String> description = Optional.of(params.get("description"));
    Optional<Long> artistId = Optional.of(Long.parseLong(params.get("artistId")));

    if (title.isEmpty() || description.isEmpty() || artistId.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.value(), "Bad Request " + artistId, null));
    }

    Optional<Artist> artist = artistRepository.findById(artistId.get());
    if (artist.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND.value(), "Not found artist id " + artistId, null));
    }

    Album album = new Album(title.get(), description.get(), artist.get());
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Inserted " + album.getTitle(), albumRepository.save(album)));
  };

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> deleteAlbum(@PathVariable Long id) {
    Optional<Album> artist = albumRepository.findById(id);
    if (artist.isPresent()) {
      albumRepository.deleteById(artist.get().getId());
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Deleted album id " + id, null ));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(200, "Not found album id " + id, null ));
  }

  @GetMapping("/{id}/songs")
  public ResponseEntity<ResponseObject> getAllSongsByAlbums(@PathVariable Long id) {
    Optional<List<Song>> songs = Optional.ofNullable(songRepository.findSongByAlbumId(id));
    if (songs.isPresent()) {
      List<SongDTO> songsDto = new ArrayList<SongDTO>();
      songs.get().forEach(song -> songsDto.add(new SongDTO(song)));
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + songsDto.size() + " song(s)", songsDto));
    };
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", new Array[0]));
  };
}
