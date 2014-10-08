package com.bg.phrobe.repository;


import com.bg.phrobe.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String lastName);
}