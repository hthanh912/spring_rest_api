package com.example.springrest.entities;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "song_id")
  private Long id;
  @Column(name = "title")
  private String title;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "artist_id")
  private Artist artist;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "album_id")
  private Album album;

  public Song() {

  }
  public Song(String title, Artist artist, Album album) {
    this.title = title;
    this.artist = artist;
    this.album = album;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getTitle() {
    return title;
  }

//  public long getAlbumId() {
//    return albumId;
//  }
//
//  public void setAlbumId(long albumId) {
//    this.albumId = albumId;
//  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

  @Override
  public String toString() {
    return "Song{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", artist'" + artist.getName() + '\'' +
//        ", albumId=" + albumId +
        '}';
  }
}
