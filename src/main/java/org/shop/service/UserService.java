package org.shop.service;

import org.shop.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = usersRepository.findByLogin(login)
//                .orElseThrow(NotFoundExceptions::new);
//        return user;
//    }
}
