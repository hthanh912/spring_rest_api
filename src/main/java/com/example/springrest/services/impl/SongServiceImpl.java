package com.example.springrest.services.impl;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.ArtistDTO;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.entities.Artist;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.SongRepository;
import com.example.springrest.services.AlbumService;
import com.example.springrest.services.ArtistService;
import com.example.springrest.services.SongService;
import exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

  private final SongRepository songRepository;
  private final ArtistService artistService;
  private final AlbumService albumService;

  private static final ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public SongServiceImpl(SongRepository songRepository, ArtistService artistService, AlbumService albumService) {
    this.songRepository = songRepository;
    this.artistService = artistService;
    this.albumService = albumService;
  }

  @Override
  public SongDTO findById(Long id) throws ResourceNotFoundException {
    Song song = this.songRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Song not found"));
    return new SongDTO(song);
  }

  @Override
  public List<SongDTO> getAllSong(Pageable pageable) {
    List<SongDTO> songDTOS = this.songRepository.findAll(pageable)
        .map(song -> new SongDTO(song))
        .toList();
    return songDTOS;
  }

  @Override
  public SongDTO insertSong(String title, Long artistId, Long albumId) throws ResourceNotFoundException {
    ArtistDTO artistDTO = this.artistService.findById(artistId);
    Artist artist = modelMapper.map(artistDTO, Artist.class);

    AlbumDTO albumDTO = this.albumService.findById(albumId);
    Album album = modelMapper.map(albumDTO, Album.class);

    Song newSong = new Song(title, artist, album);
    Song insertedSong = this.songRepository.save(newSong);
    return new SongDTO(insertedSong);
  }

}
