package com.minijuegos.login_service.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** Identificador unico del usuario **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long user_id;

    /** Nombre de usuario **/
    @Column(name = "username", nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio")
    private String username;

    /** Contraseña del usuario **/
    @Column(name = "password", nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;

    /** Nivel de la cuenta del usuario **/
    @Column(name = "level", nullable = false)
    private int level;

}
