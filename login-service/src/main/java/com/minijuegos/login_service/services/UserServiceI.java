package com.minijuegos.login_service.services;

import com.minijuegos.login_service.persistence.dto.UserDto;
import com.minijuegos.login_service.persistence.model.UserRequest;

public interface UserServiceI {

    UserDto login(UserRequest userRequest);

    UserDto register(UserRequest userRequest);

}
