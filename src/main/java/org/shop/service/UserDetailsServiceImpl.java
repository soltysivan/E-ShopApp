package org.shop.service;

import org.shop.dao.entity.ApplicationUser;
import org.shop.dao.repository.UserRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByLogin(login)
                .orElseThrow(NotFoundExceptions::new);
        return new User(applicationUser.getLogin(),
                applicationUser.getPassword(),
                applicationUser.getRoles());
    }
}
