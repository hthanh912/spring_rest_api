package com.example.springrest.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@Getter
@Setter
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "song_id")
  private Long id;
  @Column(name = "title")
  private String title;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "artist_id")
  private Artist artist;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "album_id")
  private Album album;

  @ManyToMany(mappedBy = "favoriteSongs", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
  private Set<User> users;

  public Song(String title, Artist artist, Album album) {
    this.title = title;
    this.artist = artist;
    this.album = album;
  }

}
