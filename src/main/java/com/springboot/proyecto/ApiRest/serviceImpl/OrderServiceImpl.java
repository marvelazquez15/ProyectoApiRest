package com.springboot.proyecto.ApiRest.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.proyecto.ApiRest.dto.OrderDetailDto;
import com.springboot.proyecto.ApiRest.dto.OrderDto;
import com.springboot.proyecto.ApiRest.entities.OrderDetailEntities;
import com.springboot.proyecto.ApiRest.entities.OrderEntities;
import com.springboot.proyecto.ApiRest.entities.ProductEntities;
import com.springboot.proyecto.ApiRest.entities.UserEntities;
import com.springboot.proyecto.ApiRest.exceptions.ServiceException;
import com.springboot.proyecto.ApiRest.repository.OrderRepository;
import com.springboot.proyecto.ApiRest.repository.ProductRepository;
import com.springboot.proyecto.ApiRest.repository.UserRepository;
import com.springboot.proyecto.ApiRest.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderEntities> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntities findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Orden no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public OrderEntities createOrder(OrderDto orderDto) {
        
        //validar que existan productos
        if (orderDto.getProductos() == null || orderDto.getProductos().isEmpty()) {
            throw new ServiceException("No se puede crear una orden sin productos.");
        }

        //validar usuario
        UserEntities usuario = userRepository.findById(Long.valueOf(orderDto.getFi_user_id()))
                .orElseThrow(() -> new ServiceException("Usuario no encontrado con id: " + orderDto.getFi_user_id()));

        //nueva orden y le asigna los datos básicos.
        OrderEntities nuevaOrden = new OrderEntities();
        nuevaOrden.setUser(usuario);
        nuevaOrden.setFt_fecha(LocalDateTime.now());
        nuevaOrden.setFv_metodopago(orderDto.getFv_metodopago()); 
        
        List<OrderDetailEntities> listaDetalles = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        //procesar productos
        for (OrderDetailDto itemDto : orderDto.getProductos()) {
            //precio real y el stock actual
            ProductEntities product = productRepository.findById(Long.valueOf(itemDto.getFi_product_id()))
                    .orElseThrow(() -> new ServiceException("Producto no encontrado id: " + itemDto.getFi_product_id()));

            //validar stock
            if (product.getFi_stock() < itemDto.getFi_cantidad()) {
                throw new ServiceException("Stock insuficiente para: " + product.getFv_nombre());
            }

            //descontar stock 
            product.setFi_stock(product.getFi_stock() - itemDto.getFi_cantidad());
            productRepository.save(product);

            //cálculos p*c
            BigDecimal costoDetalle = product.getFn_precio().multiply(new BigDecimal(itemDto.getFi_cantidad()));
            subtotal = subtotal.add(costoDetalle);
            
            OrderDetailEntities detalleEntidad = new OrderDetailEntities();
            detalleEntidad.setProduct(product);
            detalleEntidad.setFi_cantidad(itemDto.getFi_cantidad());
            detalleEntidad.setOrder(nuevaOrden);
            
            listaDetalles.add(detalleEntidad);
        }
        //iva 16%
        BigDecimal tasaIva = new BigDecimal("0.16");
        BigDecimal iva = subtotal.multiply(tasaIva);

        nuevaOrden.setFn_subtotal(subtotal);
        nuevaOrden.setFn_iva(iva);
        nuevaOrden.setFn_total(subtotal.add(iva));
        nuevaOrden.setDetalles(listaDetalles);

        return orderRepository.save(nuevaOrden);
    }
}
