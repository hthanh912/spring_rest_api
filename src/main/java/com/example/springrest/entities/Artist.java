package com.example.springrest.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "artists")
@NoArgsConstructor
@Getter
@Setter
public class Artist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "artist_id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany()
  @JoinColumn(name = "song_id")
  private Set<Song> listSong;

  public Artist(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
