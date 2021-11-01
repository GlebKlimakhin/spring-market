package com.gb.data.springdata.dtos;

import com.gb.data.springdata.entities.User;
import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String name;

    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getUsername();
    }
}
