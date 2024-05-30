package com.minijuegos.userfunctions.persistence.repository;

import com.minijuegos.userfunctions.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryI extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre.
     *
     * @param username Nombre de usuario a buscar.
     * @return Lista de usuarios encontrados.
     */
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);


}
