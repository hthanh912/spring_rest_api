package com.example.springrest.dto;

import com.example.springrest.entities.Song;

public class SongDTO {
  private Long id;
  private String title;
  private String artist;
  private String album;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public SongDTO(Song song){
    this.id = song.getId();
    this.title = song.getTitle();
    this.artist = song.getArtist().getName();
    this.album = song.getAlbum().getTitle();
  }
}
