package com.springboot.proyecto.ApiRest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private Long fs_id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String fv_nombre;
    
    @NotBlank(message = "El apellido paterno es obligatorio")
    private String fv_apellido_paterno;
    
    private String fv_apellido_materno;
    
    @Email(message = "Email no válido")
    @NotBlank(message = "El email es obligatorio")
    private String fv_email;
    
    private Long fn_telefono;
}