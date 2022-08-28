package com.example.springrest.services.impl;

import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.entities.Artist;
import com.example.springrest.entities.Song;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.respositories.SongRepository;
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
  private final ArtistRepository artistRepository;
  private final AlbumRepository albumRepository;

  private static final ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
    this.songRepository = songRepository;
    this.artistRepository = artistRepository;
    this.albumRepository = albumRepository;
  }

  @Override
  public SongDTO findById(Long id) throws ResourceNotFoundException {
    Song song = this.songRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Song not found"));
    return new SongDTO(song);
  }

  @Override
  public List<SongDTO> getAllSong(Pageable pageable) {
    return this.songRepository.findAll(pageable)
        .map(SongDTO::new)
        .toList();
  }

  @Override
  public List<SongDTO> getSongsByAlbumId(Long albumId) {
    return this.songRepository.findByAlbumId(albumId)
        .stream()
        .map(SongDTO::new)
        .toList();
  }

  @Override
  public List<SongDTO> getSongsByArtistId(Long artistId, Pageable pageable) {
    return this.songRepository.findSongByArtistId(artistId, pageable)
        .stream()
        .map(SongDTO::new)
        .toList();
  }
  @Override
  public SongDTO insertSong(SongDTO songDTO) {
    if (!this.artistRepository.existsById(songDTO.getArtistId())) {
      throw new ResourceNotFoundException("Not found artist id " + songDTO.getArtistId());
    }
    if (!this.albumRepository.existsById(songDTO.getAlbumId())) {
      throw new ResourceNotFoundException("Not found album id " + songDTO.getAlbumId());
    }
    Artist artist = this.artistRepository.getReferenceById(songDTO.getArtistId());
    Album album = this.albumRepository.getReferenceById(songDTO.getAlbumId());
    Song insertedSong = this.songRepository.save(new Song(songDTO.getTitle(), artist, album));
    return new SongDTO(insertedSong);
  }

  @Override
  public void deleteSong(Long id) throws ResourceNotFoundException {
    if (this.songRepository.existsById(id)){
      this.songRepository.deleteById(id);
    }
  }

}
