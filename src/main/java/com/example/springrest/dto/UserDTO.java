package com.example.springrest.dto;

import com.example.springrest.entities.Role;
import com.example.springrest.entities.Song;
import com.example.springrest.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
  private Long id;
  private String username;
  private List<String> roles = new ArrayList<>();
  private List<SongDTO> favoriteSongs = new ArrayList<>();

  public UserDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    user.getRoles().forEach(role -> roles.add(role.getName()));
    user.getFavoriteSongs().forEach(song -> favoriteSongs.add(new SongDTO(song)));
  }

}
