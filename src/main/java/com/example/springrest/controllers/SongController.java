package com.example.springrest.controllers;

import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.entities.Artist;
import com.example.springrest.entities.ResponseObject;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.respositories.SongRepository;
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
@RequestMapping("/songs")
public class SongController {
  @Autowired
  SongRepository songRepository;
  @Autowired
  ArtistRepository artistRepository;
  @Autowired
  AlbumRepository albumRepository;



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
  public ResponseEntity<ResponseObject> insertSong(@RequestParam String title, @RequestParam Long artistId, @RequestParam Long albumId) {
    Optional<Artist> artist = artistRepository.findById(artistId);
    if (artist.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND.value(), "Not found artist id " + artistId, null));
    }
    Optional<Album> album = albumRepository.findById(albumId);
    if (album.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND.value(), "Not found Album id " + artistId, null));
    }
    Song newSong = new Song(title, artist.get(), album.get());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(200, "Inserted " + newSong.getTitle(), songRepository.save(newSong)));
  };

  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject> getSongById(@PathVariable Long id) {
    Optional<Song> song = songRepository.findById(id);
    if (song.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found song id " + song.get().getId(), new SongDTO(song.get())));
    };
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", null));
  };

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseObject> deleteSong(@PathVariable Long id) {
    Optional<Song> song = songRepository.findById(id);
    if (song.isPresent()) {
      songRepository.deleteById(song.get().getId());
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Deleted song id " + id, null ));
    }
    else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(200, "Not found song id " + id, null ));
  }
}
