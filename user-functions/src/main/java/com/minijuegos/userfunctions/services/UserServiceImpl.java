package com.minijuegos.userfunctions.services;


import com.minijuegos.userfunctions.exceptions.EmptyFieldException;
import com.minijuegos.userfunctions.exceptions.UserNotFoundException;
import com.minijuegos.userfunctions.persistence.dto.UserDto;
import com.minijuegos.userfunctions.persistence.model.AuditingData;
import com.minijuegos.userfunctions.persistence.model.User;
import com.minijuegos.userfunctions.persistence.repository.UserRepositoryI;
import com.minijuegos.userfunctions.published.RabbitMQUsersProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserServiceI {

    private final UserRepositoryI userRepo;

    private final RabbitMQUsersProducer rabbitMQUsersProducer;

    @Autowired
    public UserServiceImpl(UserRepositoryI userRepo, RabbitMQUsersProducer rabbitMQUsersProducer) {
        this.userRepo = userRepo;
        this.rabbitMQUsersProducer = rabbitMQUsersProducer;
    }

    @Override
    public UserDto getUserByUsername(String username) {

        if (username.isEmpty() || username.isBlank()) {

            throw new EmptyFieldException("Username or password cannot be empty or blank");
        }

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("The user is not register in the data base"));

        return convertToDto(user);
    }

    @Override
    public String levelUp(String username) {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("The user is not register in the data base"));

        user.setLevel(user.getLevel() + 1);

        userRepo.save(user);

        return String.valueOf(user.getLevel());
    }

    /**
     * Convierte un objeto User en un objeto UserDto.
     *
     * @param user Objeto User a convertir.
     * @return Objeto UserDto convertido.
     */
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setLevel(user.getLevel());
        return userDto;
    }
}
