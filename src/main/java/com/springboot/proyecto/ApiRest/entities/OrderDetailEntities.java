package com.springboot.proyecto.ApiRest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TO004_OrderDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailEntities {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fs_id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "fi_order_id")
    @NotNull(message = "La orden asociada es obligatoria")
    private OrderEntities order;

    @ManyToOne
    @JoinColumn(name = "fi_product_id")
    @NotNull(message = "El producto es obligatorio")
    private ProductEntities product;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    private Integer fi_cantidad;
}
