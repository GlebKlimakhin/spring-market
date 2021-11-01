package com.gb.data.springdata.services;

import com.gb.data.springdata.entities.Role;
import com.gb.data.springdata.entities.User;
import com.gb.data.springdata.repositories.IRoleRepository;
import com.gb.data.springdata.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder encoder;
    private final IRoleRepository roleRepository;

    public User findByLogin(String login) {
        return userRepository.findByUsername(login);
    }

    public User saveUser(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(role));
        user.setUsername(user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if(user != null){
            if(encoder.matches(password, user.getPassword())){
                return user;
            }
        }
        return null;
    }

}
