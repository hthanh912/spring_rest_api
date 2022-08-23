package com.example.springrest.respositories;

import com.example.springrest.entities.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
  public Page<Song> findAll(Pageable pageable);
  public List<Song> findByArtistId(Long id);
  public List<Song> findByAlbumId(Long id);
}
