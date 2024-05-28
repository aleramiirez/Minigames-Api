package com.minijuegos.login_service.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    /** Nombre de usuario **/
    private String username;

    /** Contraseña del usuario **/
    private String password;

}
