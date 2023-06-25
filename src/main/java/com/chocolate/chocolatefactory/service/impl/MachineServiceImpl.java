package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.Machine;
import com.chocolate.chocolatefactory.model.MachineType;
import com.chocolate.chocolatefactory.model.Status;
import com.chocolate.chocolatefactory.repository.MachineRepository;
import com.chocolate.chocolatefactory.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;

    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository){this.machineRepository = machineRepository;}

    @Override
    public List<Machine> findAll(){
        return this.machineRepository.findAll();
    }

    @Override
    public List<Machine> findByState(Status state){
        return this.machineRepository.findAllByState(state);
    }

    @Override
    public void delete(Machine machine) throws Exception{
        if(this.machineRepository.findById(machine.getId()) == null){
            throw new Exception("Machine you are trying to delete does not exist!");
        }
        this.machineRepository.delete(machine);
    }

    @Override
    public Machine update(Machine machine) throws Exception{
        return this.machineRepository.save(machine);
    }

    @Override
    public Optional<Machine> findOne(Long id) throws Exception{
        Optional<Machine> machine =  this.machineRepository.findById(id);
        if(machine == null){
            throw new Exception("Machine with this is does not exist!");
        }
        return machine;
    }

    @Override
    public Machine create(Machine machine) throws Exception{
        if(machine.getId() != null){
            throw new Exception("ID must be null");
        }
        return this.machineRepository.save(machine);
    }

    @Override
    public List<Machine> findByType(MachineType type){
        return this.machineRepository.findAllByTypeAndStateOrderByInputQuantityDesc(type, Status.FREE);
    }

    @Override
    public Machine changeState(Long id){
        Optional<Machine> machine = this.machineRepository.findById(id);

        machine.get().setState(Status.IN_USE);
        Machine updatedMachine = this.machineRepository.save(machine.get());

        return updatedMachine;
    }
}
