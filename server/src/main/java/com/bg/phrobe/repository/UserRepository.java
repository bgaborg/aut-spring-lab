package com.bg.phrobe.repository;


import com.bg.phrobe.entities.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Long> {
    List<Users> findByUsername(String lastName);
}