package com.collectifjadore.api.repository;


import com.collectifjadore.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommonRepository  {


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}