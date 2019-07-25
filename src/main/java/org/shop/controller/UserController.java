package org.shop.controller;

import org.shop.dao.entity.ApplicationUser;
import org.shop.dao.repository.UserRepository;
import org.shop.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class UserController {

    @Autowired private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired private UserRepository userRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<ApplicationUser> saveUser(@RequestBody ApplicationUser applicationUser){
        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        return new ResponseEntity<>(userRepository.save(applicationUser), HttpStatus.OK);
    }
}
