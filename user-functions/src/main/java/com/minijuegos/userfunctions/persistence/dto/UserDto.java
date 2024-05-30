package com.minijuegos.userfunctions.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    /** Nombre de usuario **/
    private String username;

    /** Nivel de la cuenta del usuario **/
    private int level;

}
