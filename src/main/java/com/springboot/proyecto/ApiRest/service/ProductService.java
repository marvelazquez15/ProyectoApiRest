package com.springboot.proyecto.ApiRest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.proyecto.ApiRest.dto.ProductDto;
import com.springboot.proyecto.ApiRest.entities.ProductEntities;

public interface ProductService {
	public Page<ProductEntities> getProduct(Pageable pageable);
    ProductEntities findById(Long id);
    ProductEntities save(ProductEntities product);
    ProductEntities saveFromDto(ProductDto dto);
    ProductEntities updateFromDto(Long id, ProductDto dto);
}
