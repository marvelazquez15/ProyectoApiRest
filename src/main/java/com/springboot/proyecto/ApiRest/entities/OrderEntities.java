package com.springboot.proyecto.ApiRest.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TO003_Order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntities {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fs_id;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime ft_fecha;

    @ManyToOne
    @JoinColumn(name = "fi_user_id")
    @NotNull(message = "El usuario de la orden no puede ser nulo")
    private UserEntities user;

    @NotNull(message = "El subtotal es obligatorio")
    @PositiveOrZero(message = "El subtotal debe ser 0 o más")
    private BigDecimal fn_subtotal;

    @NotNull(message = "El IVA es obligatorio")
    @PositiveOrZero(message = "El IVA debe ser $0 o más")
    private BigDecimal fn_iva;

    @NotNull(message = "El total es obligatorio")
    @PositiveOrZero(message = "El total debe ser $0 o más")
    private BigDecimal fn_total;

    @NotBlank(message = "El método de pago no puede estar en blanco")
    @Size(max = 20, message = "El método de pago no debe exceder 20 caracteres")
    private String fv_metodopago;

    @NotEmpty(message = "La orden debe tener al menos un detalle")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference // <--- AGREGA ESTO
    private List<OrderDetailEntities> detalles;
}
