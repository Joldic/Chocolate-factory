package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.Machine;
import com.chocolate.chocolatefactory.model.MachineType;
import com.chocolate.chocolatefactory.model.Status;

import java.util.List;
import java.util.Optional;

public interface MachineService {
    List<Machine> findAll();

    List<Machine> findByState(Status state);

    void delete(Machine machine) throws Exception;

    Machine update(Machine machine) throws Exception;

    Optional<Machine> findOne(Long id) throws Exception;

    Machine create(Machine machine) throws Exception;

    List<Machine> findByType(MachineType type);

    Machine changeState(Long id);
}
