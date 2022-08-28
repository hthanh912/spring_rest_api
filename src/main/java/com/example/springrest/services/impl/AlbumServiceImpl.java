package com.example.springrest.services.impl;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Album;
import com.example.springrest.entities.Artist;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.services.AlbumService;
import com.example.springrest.services.ArtistService;
import com.example.springrest.services.SongService;

import exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

  private final AlbumRepository albumRepository;
  private final ArtistRepository artistRepository;
  private final SongService songService;
  private static final ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public AlbumServiceImpl(
      AlbumRepository albumRepository,
      ArtistRepository artistRepository,
      @Lazy SongService songService,
      @Lazy ArtistService artistService)
  {
    this.albumRepository = albumRepository;
    this.artistRepository = artistRepository;
    this.songService = songService;
  }

  @Override
  public List<AlbumDTO> getAllAlbum() throws ResourceNotFoundException {
    return albumRepository.findAll().stream().map(AlbumDTO::new).toList();
  }

  @Override
  public AlbumDTO findById(Long id) throws ResourceNotFoundException {
    Album album = albumRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found album id " + id));
    return new AlbumDTO(album);
  }

  @Override
  public List<SongDTO> getAllSongByAlbumId(Long albumId) throws ResourceNotFoundException {
    return this.songService.getSongsByAlbumId(albumId);
  }

  @Override
  public AlbumDTO insertAlbum(AlbumDTO albumDTO) {
    if (!this.artistRepository.existsById(albumDTO.getArtistId())) {
      throw new ResourceNotFoundException("Not found artist id " + albumDTO.getArtistId());
    }
    Artist artist = this.artistRepository.getReferenceById(albumDTO.getArtistId());
    Album album = modelMapper.map(albumDTO, Album.class);
    album.setArtist(artist);
    Album insertedAlbum = this.albumRepository.save(album);
    return new AlbumDTO(insertedAlbum);
  }

  @Override
  public void deleteAlbum(Long id) throws ResourceNotFoundException {
    if (this.albumRepository.existsById(id)) {
      this.albumRepository.deleteById(id);
    } else throw new ResourceNotFoundException("Not found album id " + id);
  }
}
