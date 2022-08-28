package com.example.springrest.dto;

import com.example.springrest.entities.Song;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
  private Long id;
  private String title;
  private Long artistId;
  private String artist;
  private Long albumId;
  private String album;

  public SongDTO(Song song){
    this.id = song.getId();
    this.title = song.getTitle();
    this.artistId = song.getArtist().getId();
    this.artist = song.getArtist().getName();
    this.albumId = song.getAlbum().getId();
    this.album = song.getAlbum().getTitle();
  }
}
