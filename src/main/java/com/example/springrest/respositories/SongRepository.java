package com.example.springrest.respositories;

import com.example.springrest.entities.Song;
import com.example.springrest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
  public Optional<List<Song>> findSongByArtistId(Long id);
  public Optional<List<Song>> findSongByAlbumId(Long id);
}
