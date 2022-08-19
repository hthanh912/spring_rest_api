package com.example.springrest.dto;

import com.example.springrest.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {
  private Long id;
  private String username;
  private List<String> roles = new ArrayList<>();
  private List<SongDTO> favoriteSongs = new ArrayList<>();

  public UserDTO(@NotNull User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    user.getRoles().forEach(role -> this.roles.add(role.getName()));
    user.getFavoriteSongs().forEach(song -> this.favoriteSongs.add(new SongDTO(song)));
  }

}
