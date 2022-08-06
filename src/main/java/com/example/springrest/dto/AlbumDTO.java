package com.example.springrest.dto;

import com.example.springrest.entities.Album;

public class AlbumDTO {
  private Long id;
  private String title;
  private String description;
  private Long artistId;
  private String artistName;


  public AlbumDTO(Album album) {
    this.id = album.getId();
    this.title = album.getTitle();
    this.description = album.getDescription();
    this.artistId = album.getArtist().getId();
    this.artistName = album.getArtist().getName();
  }

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public Long getArtistId() {
    return artistId;
  }

  public void setArtistId(Long artistId) {
    this.artistId = artistId;
  }
}
