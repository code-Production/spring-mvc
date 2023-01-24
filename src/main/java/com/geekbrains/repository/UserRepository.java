package com.geekbrains.repository;

import com.geekbrains.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Long> {
    User findByUsername(String username);
}
