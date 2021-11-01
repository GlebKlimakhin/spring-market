package com.gb.data.springdata.security;

import com.gb.data.springdata.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public CustomUserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return CustomUserDetails.fromUserToCustomUserDetails(service.findByLogin(name));
    }

}
