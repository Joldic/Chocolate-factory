package com.chocolate.chocolatefactory.controller;


import com.chocolate.chocolatefactory.model.Machine;
import com.chocolate.chocolatefactory.model.MachineType;
import com.chocolate.chocolatefactory.model.Status;
import com.chocolate.chocolatefactory.model.dto.MachineDTO;
import com.chocolate.chocolatefactory.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "api/machines")
public class MachineController {
    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {this.machineService = machineService;}

    //getOne
    @GetMapping(value="/getMachine/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<MachineDTO> getMachine(@PathVariable Long id) throws Exception{
        Optional<Machine> machine = this.machineService.findOne(id);

        MachineDTO machineDTO = createDTOInstance(machine.get());

        return new ResponseEntity<>(machineDTO, HttpStatus.OK);
    }
    //getAll
    @GetMapping(value = "/getAllMachines", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<MachineDTO>> getAllMachines(){
        List<Machine> machines = this.machineService.findAll();

        List<MachineDTO> machineDTOS = new ArrayList<MachineDTO>();

        for(Machine m : machines){
            MachineDTO machineDTO = createDTOInstance(m);
            machineDTOS.add(machineDTO);
        }

        return new ResponseEntity<>(machineDTOS, HttpStatus.OK);
    }
    //create
    @PostMapping(value = "/createNewMachine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<MachineDTO> createMachine(@RequestBody MachineDTO machineDTO) throws Exception{
        Machine machine = new Machine(
                machineDTO.getName(),
                machineDTO.getInputQuantity(),
                Status.valueOf(machineDTO.getState()),
                machineDTO.getWorkingDays()
        );
        machine.setType(MachineType.valueOf(machineDTO.getType()));

        Machine newMachine = this.machineService.create(machine);

        MachineDTO newMachineDTO = createDTOInstance(newMachine);

        return new ResponseEntity<>(newMachineDTO, HttpStatus.CREATED);
    }
    //delete
    @DeleteMapping(value = "/deleteMachine/{id}")
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity deleteMachine(@PathVariable Long id) throws Exception{
        Optional<Machine> machine = this.machineService.findOne(id);
        this.machineService.delete(machine.get());

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    //update
    @PutMapping(value = "/updateMachine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<MachineDTO> updateMachine(@RequestBody MachineDTO machineDTO) throws Exception{
        Optional<Machine> oldMachine = this.machineService.findOne(machineDTO.getId());

        Machine newMachine = createRegularInstance(machineDTO);

        Machine updatedMachine = this.machineService.update(newMachine);
        MachineDTO updatedMachineDTO =  createDTOInstance(updatedMachine);

        return new ResponseEntity<>(updatedMachineDTO, HttpStatus.OK);
    }

    public MachineDTO createDTOInstance(Machine machine){
        MachineDTO ret_val = new MachineDTO(
                machine.getId(),
                machine.getName(),
                machine.getInputQuantity(),
                String.valueOf(machine.getState()),
                machine.getWorkingDays()
        );
        ret_val.setType(String.valueOf(machine.getType()));
        return ret_val;
    }

    public Machine createRegularInstance(MachineDTO machineDTO){
        Machine machine = new Machine(
            machineDTO.getId(),
            machineDTO.getName(),
            machineDTO.getInputQuantity(),
            Status.valueOf(machineDTO.getState()),
           // Status.FREE,
            machineDTO.getWorkingDays()
        );
        return machine;
    }
}
