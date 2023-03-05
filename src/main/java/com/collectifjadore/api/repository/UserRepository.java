package com.collectifjadore.api.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.collectifjadore.api.models.User;

public interface UserRepository extends MongoRepository<User, String>,CommonRepository {
    Optional<User> findByUsername(String username);
    List<User> findAll();
    Optional<User> findById( String id);


}