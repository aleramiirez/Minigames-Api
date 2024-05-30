package com.minijuegos.login_service.services;

import com.minijuegos.login_service.exceptions.EmptyFieldException;
import com.minijuegos.login_service.exceptions.InvalidCredentialsException;
import com.minijuegos.login_service.exceptions.UserNotFoundException;
import com.minijuegos.login_service.exceptions.UsernameAlreadyExistsException;
import com.minijuegos.login_service.persistence.dto.UserDto;
import com.minijuegos.login_service.persistence.model.AuditingData;
import com.minijuegos.login_service.persistence.model.User;
import com.minijuegos.login_service.persistence.model.UserRequest;
import com.minijuegos.login_service.persistence.repository.UserRepositoryI;
import com.minijuegos.login_service.published.RabbitMQJsonProducer;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl implements UserServiceI {

    private final UserRepositoryI userRepo;

    private final RabbitMQJsonProducer rabbitMQProducer;

    @Autowired
    public UserServiceImpl(UserRepositoryI userRepo, RabbitMQJsonProducer rabbitMQProducer) {
        this.userRepo = userRepo;
        this.rabbitMQProducer = rabbitMQProducer;
    }


    @Override
    public UserDto login(UserRequest userRequest) {

        if (userRequest.getUsername().isEmpty() ||
                userRequest.getUsername().isBlank() ||
                userRequest.getPassword().isEmpty() ||
                userRequest.getPassword().isBlank()) {
            throw new EmptyFieldException("Username or password cannot be empty or blank");
        }

        User user = userRepo.findByUsername(userRequest.getUsername())
                    .orElseThrow(() -> new UserNotFoundException("The user is not register in the data base"));

        if (BCrypt.checkpw(userRequest.getPassword(), user.getPassword())) {

            userRepo.save(user);

            AuditingData auditingData = new AuditingData();

            auditingData.setCreatedBy(user.getUsername());
            auditingData.setCreatedDate(LocalDateTime.now());
            auditingData.setTypeRequest("auth/login");

            String data = auditingData.toString();

            rabbitMQProducer.sendJsonMessage(data);

            return convertToDto(user);
        } else {
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }

    @Override
    public UserDto register(UserRequest userRequest) {

        User user = new User();

        if (userRequest.getUsername().isEmpty() ||
                userRequest.getUsername().isBlank() ||
                userRequest.getPassword().isEmpty() ||
                userRequest.getPassword().isBlank()) {
            throw new EmptyFieldException("Username or password cannot be empty or blank");
        }

        if (userRepo.existsByUsername(userRequest.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        String hashedPassword = BCrypt.hashpw(
                userRequest.getPassword(), BCrypt.gensalt());

        user.setUsername(userRequest.getUsername());
        user.setPassword(hashedPassword);
        user.setLevel(1);

        userRepo.save(user);

        AuditingData auditingData = new AuditingData();

        auditingData.setCreatedBy(user.getUsername());
        auditingData.setCreatedDate(LocalDateTime.now());
        auditingData.setTypeRequest("auth/register");

        String data = auditingData.toString();

        rabbitMQProducer.sendJsonMessage(data);

        return convertToDto(user);
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
