package com.example.springrest.entities;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "song_id")
  private long id;
  @Column(name = "title")
  private String title;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "artist_id")
  private Artist artist;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "album_id")
  private Album album;

//  @Column(name = "album_id")
//  private long albumId;

  public Artist getArtist() {
    return artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
