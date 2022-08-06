package com.example.springrest.entities;

import javax.persistence.*;

@Entity
@Table(name = "albums")
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

  public Album(){

  }
  public Album(String title, String description, Artist artist) {
    this.title = title;
    this.description = description;
    this.artist = artist;
  }

  public Artist getArtist() {
    return artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
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

  @Override
  public String toString() {
    return "Album{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
