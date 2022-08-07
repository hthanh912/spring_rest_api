package com.example.springrest.respositories;

import com.example.springrest.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
  public List<Album> findAllByArtistId(Long id);
}
