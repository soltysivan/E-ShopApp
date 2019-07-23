package org.shop.controller;

import org.shop.dao.repository.UserRepository;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistration {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
}
