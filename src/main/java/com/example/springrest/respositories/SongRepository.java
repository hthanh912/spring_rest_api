package com.example.springrest.respositories;

import com.example.springrest.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
}
