package com.example.springrest.services.impl;

import com.example.springrest.dto.AlbumDTO;
import com.example.springrest.dto.ArtistDTO;
import com.example.springrest.dto.SongDTO;
import com.example.springrest.entities.Artist;
import com.example.springrest.respositories.AlbumRepository;
import com.example.springrest.respositories.ArtistRepository;
import com.example.springrest.services.ArtistService;
import com.example.springrest.services.SongService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;

import java.util.List;


@Service
public class ArtistServiceImpl implements ArtistService {

  private final ArtistRepository artistRepository;
  private final AlbumRepository albumRepository;
  private final SongService songService;

  @Autowired
  public ArtistServiceImpl(ArtistRepository artistRepository, AlbumRepository albumRepository, @Lazy SongService songService) {
    this.artistRepository = artistRepository;
    this.albumRepository = albumRepository;
    this.songService = songService;
  }

  @Override
  public List<ArtistDTO> getAllArtist() throws ResourceNotFoundException {
    return this.artistRepository.findAll().stream().map(ArtistDTO::new).toList();
  }

  @Override
  public ArtistDTO getArtistById(Long id) throws ResourceNotFoundException {
    Artist artist = this.artistRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found artist id " + id));
    return new ArtistDTO(artist);
  }

  @Override
  public List<SongDTO> getSongByArtistId(Long id, Pageable pageable) throws ResourceNotFoundException {
    return this.songService.getSongsByArtistId(id, pageable);
  }

  @Override
  public List<AlbumDTO> getAlbumByArtistId(Long artisId) throws ResourceNotFoundException {
    if (!this.artistRepository.existsById(artisId)) {
      throw new ResourceNotFoundException("Not found artist id " + artisId);
    }
    return this.albumRepository.findAllByArtistId(artisId).stream().map(AlbumDTO::new).toList();
  }

  @Override
  public ArtistDTO insertArtist(Artist artist) {
    Artist insertedArtist = this.artistRepository.save(artist);
    return new ArtistDTO(insertedArtist);
  }

  @Override
  public void deleteArtist(Long id) {
    if (!this.artistRepository.existsById(id)){
      throw new ResourceNotFoundException("Not found artist id " + id);
    }
    this.artistRepository.deleteById(this.artistRepository.findById(id).get().getId());
  }
}
