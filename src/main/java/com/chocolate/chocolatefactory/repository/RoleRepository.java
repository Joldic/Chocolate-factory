package com.chocolate.chocolatefactory.repository;

import java.util.List;

import com.chocolate.chocolatefactory.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
    List<Role> findByName(String name);
}
