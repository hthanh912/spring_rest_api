package com.example.springrest.dto;

import com.example.springrest.entities.Song;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDTO {
  private Long id;
  private String title;
  private String artist;
  private String album;

  public SongDTO(Song song){
    this.id = song.getId();
    this.title = song.getTitle();
    this.artist = song.getArtist().getName();
    this.album = song.getAlbum().getTitle();
  }
}
