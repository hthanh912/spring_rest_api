package com.example.springrest.entities;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "artist_id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

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

  @Override
  public String toString() {
    return "Artist{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
