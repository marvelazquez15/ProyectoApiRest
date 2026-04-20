package com.springboot.proyecto.ApiRest.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TO002_Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntities {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fs_id;

    @NotBlank(message = "El nombre del producto es obligatorio*")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres*")
    private String fv_nombre;

    @Size(max = 100, message = "La descripción no puede exceder los 100 caracteres*")
    private String fv_descripcion;

    @NotBlank(message = "La marca es obligatoria*")
    @Size(max = 50, message = "La marca no puede exceder los 50 caracteres*")
    private String fv_marca;

    @NotNull(message = "El precio es obligatorio*")
    @PositiveOrZero(message = "El precio debe ser igual o mayor a 0*")
    private BigDecimal fn_precio;

    @NotNull(message = "El stock es obligatorio*")
    @Min(value = 0, message = "El stock no puede ser negativo*")
    private Integer fi_stock;
}
