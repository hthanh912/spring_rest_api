package com.example.springrest.dto;

import com.example.springrest.entities.Role;
import com.example.springrest.entities.Song;
import com.example.springrest.entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
  private Long id;
  private String username;
  private List<String> roles = new ArrayList<String>();

  private List<SongDTO> favoriteSongs = new ArrayList<SongDTO>();

  public UserDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    user.getRoles().forEach(role -> roles.add(role.getName()));
    user.getFavoriteSongs().forEach(song -> favoriteSongs.add(new SongDTO(song)));
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<SongDTO> getFavoriteSongs() {
    return favoriteSongs;
  }

  public void setFavoriteSongs(List<SongDTO> favoriteSongs) {
    this.favoriteSongs = favoriteSongs;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", roles=" + roles +
        ", favoriteSongs=" + favoriteSongs +
        '}';
  }
}
