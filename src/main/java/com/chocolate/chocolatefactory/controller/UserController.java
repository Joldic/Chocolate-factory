package com.chocolate.chocolatefactory.controller;

import com.chocolate.chocolatefactory.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chocolate.chocolatefactory.model.dto.UserDTO;
import com.chocolate.chocolatefactory.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(value ="/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userServiceImpl.findAll();
        List<UserDTO> usersDTO = new ArrayList<UserDTO>();

        for(User user : users){
            UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getUsername());
            usersDTO.add(userDTO);
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/findOne/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
        Optional<User> user = this.userServiceImpl.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO userDTO = new UserDTO(user.get().getFirstName(), user.get().getLastName(), user.get().getEmail(), user.get().getPassword(), user.get().getUsername());
        userDTO.setUsername(user.get().getUsername());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/createManager", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createManager(@RequestBody UserDTO userDTO) throws Exception{
        return new ResponseEntity<>(userServiceImpl.createManager(userDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/createRegularUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createRegularUser(@RequestBody UserDTO userDTO) throws Exception{
        return new ResponseEntity<>(userServiceImpl.createRegularUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}/{userId}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @PathVariable Long userId, @RequestBody UserDTO userDTO) throws Exception{

        if(userServiceImpl.findById(userId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userServiceImpl.update(userDTO, userId), HttpStatus.OK);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,  @RequestBody UserDTO userDTO) throws Exception{

        if(userServiceImpl.findById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userServiceImpl.updateUser(userDTO, id), HttpStatus.OK);
    }




}
