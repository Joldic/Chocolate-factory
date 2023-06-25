package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.Machine;
import com.chocolate.chocolatefactory.model.MachineType;
import com.chocolate.chocolatefactory.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    Optional<Machine> findById(Long id);

    List<Machine> findAll();

    List<Machine> findAllByState(Status state);

    List<Machine> findAllByTypeAndStateOrderByInputQuantityDesc(MachineType type, Status state);
}
