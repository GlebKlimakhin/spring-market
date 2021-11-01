package com.gb.data.springdata.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDto {

    private String login;
    private String password;
}
