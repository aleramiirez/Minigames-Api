package com.minijuegos.userfunctions.services;

import com.minijuegos.userfunctions.persistence.dto.UserDto;

public interface UserServiceI {

    UserDto getUserByUsername(String username);

}
