package com.example.springrest.dto;

import com.example.springrest.entities.Artist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtistDTO {
  private Long id;
  private String name;
  private String description;

  public ArtistDTO(Artist artist) {
    this.id = artist.getId();
    this.name = artist.getName();
    this.description = artist.getDescription();
  }
}
