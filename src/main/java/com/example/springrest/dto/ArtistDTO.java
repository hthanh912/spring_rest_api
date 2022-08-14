package com.example.springrest.dto;

import com.example.springrest.entities.Artist;

import javax.persistence.Column;

public class ArtistDTO {
  private Long id;
  private String name;
  private String description;

  public ArtistDTO(Artist artist) {
    this.id = artist.getId();
    this.name = artist.getName();
    this.description = artist.getDescription();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
