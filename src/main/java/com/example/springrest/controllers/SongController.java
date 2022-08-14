package com.example.springrest.controllers;

import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.entities.Artist;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.respositories.SongRepository;
import com.example.springrest.services.SongService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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
  public ResponseEntity<ResponseObject> insertSong(@RequestParam String title, @RequestParam Long artistId, @RequestParam Long albumId) {
//    Optional<Artist> artist = artistRepository.findById(artistId);
//    if (artist.isEmpty()) {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND.value(), "Not found artist id " + artistId, null));
//    }
//    Optional<Album> album = albumRepository.findById(albumId);
//    if (album.isEmpty()) {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND.value(), "Not found Album id " + artistId, null));
//    }
//    Song newSong = new Song(title, artist.get(), album.get());
//
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(200, "Inserted " + newSong.getTitle(), songRepository.save(newSong)));
    SongDTO insertedSong = this.songService.insertSong(title, artistId, albumId);
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
    Optional<Song> song = songRepository.findById(id);
    if (song.isPresent()) {
      songRepository.deleteById(song.get().getId());
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Deleted song id " + id, null));
    } else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(200, "Not found song id " + id, null));
  }
}
