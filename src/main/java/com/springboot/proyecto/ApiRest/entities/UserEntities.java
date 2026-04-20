package com.springboot.proyecto.ApiRest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TO001_User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntities {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fs_id;

    @NotBlank(message = "El nombre es obligatorio*")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres*")
    private String fv_nombre;

    @NotBlank(message = "El apellido paterno es obligatorio*")
    @Size(max = 50, message = "El apellido paterno no puede exceder los 50 caracteres*")
    private String fv_apellido_paterno;

    @Size(max = 50, message = "El apellido materno no puede exceder los 50 caracteres*")
    private String fv_apellido_materno;

    @NotBlank(message = "El email es obligatorio*")
    @Email(message = "Debe proporcionar un formato de email válido*")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres*")
    private String fv_email;

    @NotNull(message = "El teléfono es obligatorio*")
    @Digits(integer = 10, fraction = 0, message = "El teléfono debe ser numérico de 10 dígitos*")
    private Long fn_telefono;
}
