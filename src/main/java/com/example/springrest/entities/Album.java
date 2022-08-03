package com.example.springrest.entities;

import javax.persistence.*;

@Entity
@Table(name = "albums")
public class Album {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "album_id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

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
}
