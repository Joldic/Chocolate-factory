package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.Role;
import com.chocolate.chocolatefactory.model.dto.UserDTO;
import com.chocolate.chocolatefactory.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.chocolate.chocolatefactory.repository.UserRepository;
import com.chocolate.chocolatefactory.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public UserDTO createManager(UserDTO userDTO) throws Exception {

        User manager = new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getUsername()
        );

        User newManager = this.userRepository.save(manager);

        UserDTO newManagerDTO = new UserDTO(
                newManager.getFirstName(),
                newManager.getLastName(),
                newManager.getEmail(),
                newManager.getPassword(),
                newManager.getUsername()
        );

        return newManagerDTO;
    }

    public UserDTO createRegularUser(UserDTO userDTO) throws Exception {

        User regularUser = new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getUsername()
        );

        User newRegularUser = this.userRepository.save(regularUser);

        UserDTO newRegularUserDTO = new UserDTO(
                newRegularUser.getFirstName(),
                newRegularUser.getLastName(),
                newRegularUser.getEmail(),
                newRegularUser.getPassword(),
                newRegularUser.getUsername()
        );
        return newRegularUserDTO;
    }

    public UserDTO update(UserDTO userDTO, Long userId) throws Exception {

        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getUsername());
        user.setId(userId);
        User updatedUser = userRepository.save(user);
        UserDTO updateduserDTO = new UserDTO(updatedUser.getFirstName(), updatedUser.getFirstName(), updatedUser.getEmail(), updatedUser.getPassword(), updatedUser.getUsername());
        return updateduserDTO;
    }

    public UserDTO updateUser(UserDTO userDTO, Long id) throws Exception{
        Optional<User> user = userRepository.findById(id);

        if(!userDTO.getEmail().equals(""))
            user.get().setEmail(userDTO.getEmail());
        if(!userDTO.getPassword().equals(""))
            user.get().setPassword(userDTO.getPassword());
        if(!userDTO.getFirstName().equals(""))
            user.get().setFirstName(userDTO.getFirstName());
        if(!userDTO.getLastName().equals(""))
            user.get().setLastName(userDTO.getLastName());
        if(!userDTO.getUsername().equals(""))
            user.get().setUsername(userDTO.getUsername());

        User user2 = new User(user.get().getFirstName(), user.get().getLastName(), user.get().getEmail(), user.get().getPassword(), user.get().getUsername());
        User updatedUser = userRepository.save(user2);

        UserDTO updatedUserDTO = new UserDTO(updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail(), updatedUser.getPassword(), user.get().getUsername());
        updatedUserDTO.setUsername(updatedUser.getUsername());

        return updatedUserDTO;

    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User save(UserDTO userDTO) {
        User u = new User();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setEmail(userDTO.getEmail());
        u.setUsername(userDTO.getUsername());
        u.setEnabled(true);

        List<Role> roles = roleService.findByName("ROLE_SYSTEM_ADMIN");
        u.setRoles(roles);

        return this.userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }
}
