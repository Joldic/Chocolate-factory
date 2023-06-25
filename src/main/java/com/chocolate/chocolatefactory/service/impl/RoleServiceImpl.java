package com.chocolate.chocolatefactory.service.impl;

import java.util.List;

import com.chocolate.chocolatefactory.model.Role;
import com.chocolate.chocolatefactory.repository.RoleRepository;
import com.chocolate.chocolatefactory.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id) {
        return this.roleRepository.getOne(id);
    }

    public List<Role> findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
