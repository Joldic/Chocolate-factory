package com.chocolate.chocolatefactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chocolate.chocolatefactory.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    List<User> findAll();

    User findByEmail(String email);

    User findByUsername(String username);
}
