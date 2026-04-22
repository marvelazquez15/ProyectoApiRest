package com.springboot.proyecto.ApiRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.proyecto.ApiRest.dto.OrderDto;
import com.springboot.proyecto.ApiRest.entities.OrderEntities;
import com.springboot.proyecto.ApiRest.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    // Endpoint: GET /api/orders - Consultar todas las órdenes de venta realizadas.
    public ResponseEntity<List<OrderEntities>> getAll() {
        return ResponseEntity.ok(orderService.findAll()); //retorna la lista completa de productos
    }

    @GetMapping("/{id}")
    // Endpoint: GET /api/orders/{id} - Buscar una orden específica por su ID.
    public ResponseEntity<OrderEntities> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }
    
    @PostMapping
    // Endpoint: POST /api/orders - Generar una nueva orden de venta.
    public ResponseEntity<OrderEntities> create(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }
}