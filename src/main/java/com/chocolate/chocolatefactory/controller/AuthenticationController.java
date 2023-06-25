package com.chocolate.chocolatefactory.controller;

import javax.servlet.http.HttpServletResponse;

import com.chocolate.chocolatefactory.exception.ResourceConflictException;
import com.chocolate.chocolatefactory.model.User;
import com.chocolate.chocolatefactory.model.dto.JwtAuthenticationRequest;
import com.chocolate.chocolatefactory.model.dto.UserDTO;
import com.chocolate.chocolatefactory.model.dto.UserTokenState;
import com.chocolate.chocolatefactory.service.impl.UserServiceImpl;
import com.chocolate.chocolatefactory.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername(), user.getId());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO, UriComponentsBuilder ucBuilder) {

        User existUser = this.userServiceImpl.findByUsername(userDTO.getUsername());

        if (existUser != null) {
            throw new ResourceConflictException(userDTO.getId(), "Username already exists");
        }

        User user = this.userServiceImpl.save(userDTO);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
