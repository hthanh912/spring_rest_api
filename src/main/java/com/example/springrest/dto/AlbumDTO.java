package com.example.springrest.dto;

import com.example.springrest.entities.Album;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
}
