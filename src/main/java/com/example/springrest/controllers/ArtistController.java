package com.example.springrest.controllers;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.ArtistDTO;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.entities.Artist;
import com.example.springrest.dto.ResponseObject;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.respositories.SongRepository;
import com.example.springrest.services.ArtistService;
import com.example.springrest.services.SongService;
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
@RequestMapping("/artists")
public class ArtistController {
  @Autowired
  ArtistRepository artistRepository;

  @Autowired
  SongRepository songRepository;
  @Autowired
  AlbumRepository albumRepository;

  private final ArtistService artistService;

  @Autowired
  public ArtistController(ArtistService artistService) {
    this.artistService = artistService;
  }

  @GetMapping("")
  public ResponseEntity<ResponseObject> getAllArtists() {
    Optional<List<Artist>> artists = Optional.of(artistRepository.findAll());
    if (artists.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + artists.get().size() + " artist(s)", artists.get()));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", new Array[0]));
  }

  @PostMapping(value = "")
  public ResponseEntity<ResponseObject> insertArtist(@RequestBody Artist artist) {
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Inserted " + artist.getName(), artistRepository.save(artist)));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> getArtistById(@PathVariable Long id) {
    ArtistDTO artistDTO = this.artistService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + artistDTO.getName(), artistDTO));
  }

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
  public ResponseEntity<ResponseObject> getAllSongsByArtist(@PathVariable Long id, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @RequestParam(required = false) String orderBy) {
    // Integer pageParam = Optional.ofNullable(page).orElse(0);
    // Integer sizeParam = Optional.ofNullable(size).orElse(2);

    // Optional<String> orderByParam = Optional.ofNullable(orderBy);
    // Sort.Direction order = Sort.Direction.ASC;
    // if (orderByParam.isPresent()) {
    //   System.out.println(orderByParam.get());
    //   if (orderByParam.get().equals("desc")) {
    //     order = Sort.Direction.DESC;
    //   }
    // }

    // Pageable pageable = PageRequest.of(pageParam, sizeParam, Sort.by(order, "title"));
    // Optional<Page<Song>> songs = Optional.ofNullable(songRepository.findSongByArtistId(id, pageable));
    // if (songs.isPresent()) {
    //   List<SongDTO> songsDto = new ArrayList<SongDTO>();
    //   songs.get().forEach(song -> songsDto.add(new SongDTO(song)));
    //   return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Found " + songsDto.size() + " song(s) in page " + page, songsDto));
    // }
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(400, "Not Found", new Array[0]));
    List<SongDTO> listSong = this.songService.getSongByArtistId(id);
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Found ", listSong));
  }

}
