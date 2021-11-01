package com.gb.data.springdata.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SignUpRequestDto {
    private String login;
    private String password;
}
