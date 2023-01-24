package com.geekbrains.service;

import com.geekbrains.model.Role;
import com.geekbrains.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
 }
