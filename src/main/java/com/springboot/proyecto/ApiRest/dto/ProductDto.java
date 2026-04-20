package com.springboot.proyecto.ApiRest.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDto {
    private Long fs_id;
    
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String fv_nombre;
    
    private String fv_descripcion;
    private String fv_marca;
    
    @Min(value = 0, message = "El precio no puede ser negativo")
    private BigDecimal fn_precio;
    
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer fi_stock;
}
