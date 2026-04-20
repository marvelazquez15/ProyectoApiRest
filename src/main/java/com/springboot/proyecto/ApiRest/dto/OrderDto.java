package com.springboot.proyecto.ApiRest.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDto {
	@NotNull(message = "El ID de usuario es obligatorio")
    private Integer fi_user_id;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 20, message = "El método de pago no puede exceder los 20 caracteres")
    private String fv_metodopago;

    @NotEmpty(message = "La orden debe contener al menos un producto")
    @Valid 
    private List<OrderDetailDto> productos;
}
