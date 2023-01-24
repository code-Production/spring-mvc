package com.geekbrains.repository;

import com.geekbrains.model.Role;
import org.springframework.data.repository.ListCrudRepository;

public interface RoleRepository extends ListCrudRepository<Role, Long> {
}
