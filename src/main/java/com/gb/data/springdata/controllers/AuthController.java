package com.gb.data.springdata.controllers;

import com.gb.data.springdata.dtos.AuthRequestDto;
import com.gb.data.springdata.dtos.AuthResponse;
import com.gb.data.springdata.dtos.SignUpRequestDto;
import com.gb.data.springdata.entities.User;
import com.gb.data.springdata.security.jwt.JwtProvider;
import com.gb.data.springdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public String registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getLogin());
        user.setPassword(signUpRequest.getPassword());
        userService.saveUser(user);
        return HttpStatus.OK.toString();
    }

    @PostMapping("/signin")
    public AuthResponse auth(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
