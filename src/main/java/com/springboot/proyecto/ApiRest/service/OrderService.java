package com.springboot.proyecto.ApiRest.service;

import java.util.List;

import com.springboot.proyecto.ApiRest.dto.OrderDto;
import com.springboot.proyecto.ApiRest.entities.OrderEntities;

public interface OrderService {
	List<OrderEntities> findAll();
    OrderEntities findById(Long id);
    OrderEntities createOrder(OrderDto orderDTO);
}
