package com.chocolate.chocolatefactory.service;

import java.util.List;

import com.chocolate.chocolatefactory.model.Role;
import com.chocolate.chocolatefactory.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface RoleService {

    Role findById (Long id);

    List<Role> findByName(String name);
}
