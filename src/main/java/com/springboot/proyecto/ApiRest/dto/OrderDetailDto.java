package com.springboot.proyecto.ApiRest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDetailDto {
	@NotNull(message = "El ID del producto es obligatorio")
    private Integer fi_product_id;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    private Integer fi_cantidad;
}
