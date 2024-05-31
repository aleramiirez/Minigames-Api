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
    public UserDto getUserByUsername(String username, String usernameRequest) {

        if (username.isEmpty() || username.isBlank()) {

            throw new EmptyFieldException("Username or password cannot be empty or blank");
        }

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("The user is not register in the data base"));

        AuditingData auditingData = new AuditingData();

        auditingData.setCreatedBy(usernameRequest);
        auditingData.setCreatedDate(LocalDateTime.now());
        auditingData.setTypeRequest("api/v1/users/getUser/" + username);

        String[] usernameData = auditingData.getCreatedBy().split(":");

        usernameData[1].replace("\"", "").trim();

        auditingData.setCreatedBy(usernameData[1]);

        rabbitMQUsersProducer.sendUsersMessage(auditingData.toString());

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
