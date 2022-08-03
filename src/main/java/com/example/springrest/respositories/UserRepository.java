package com.example.springrest.respositories;

import com.example.springrest.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.username = :username")
  public Optional<User> findUserByUsername(@Param("username") String username);
}