package com.example.springrest.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "albums")
@NoArgsConstructor
@Getter
@Setter
public class Album {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "album_id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @ManyToOne()
  @JoinColumn(name = "artist_id")
  private Artist artist;

  public Album(String title, String description, Artist artist) {
    this.title = title;
    this.description = description;
    this.artist = artist;
  }

}
