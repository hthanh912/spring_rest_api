package com.example.springrest.respositories;

import com.example.springrest.entities.Song;
import com.example.springrest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
  public Song findSongByArtistId(Long id);
}
