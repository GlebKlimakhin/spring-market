package com.gb.data.springdata.controllers;

import com.gb.data.springdata.dtos.UserDto;
import com.gb.data.springdata.entities.User;
import com.gb.data.springdata.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    public final IUserRepository repository;

    @GetMapping
    public List<UserDto> user(){
        return repository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public User user(@RequestBody User user){
        return repository.save(user);
    }



}
