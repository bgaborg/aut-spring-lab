package com.bg.phrobe.repository;


import com.bg.phrobe.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String lastName);
}