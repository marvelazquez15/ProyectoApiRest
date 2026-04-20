package com.springboot.proyecto.ApiRest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.proyecto.ApiRest.dto.ProductDto;
import com.springboot.proyecto.ApiRest.entities.ProductEntities;
import com.springboot.proyecto.ApiRest.exceptions.ServiceException;
import com.springboot.proyecto.ApiRest.repository.ProductRepository;
import com.springboot.proyecto.ApiRest.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntities findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Producto no encontrado con id: " + id));
    }

    @Override
    public ProductEntities save(ProductEntities product) {
        return productRepository.save(product);
    }

    @Override
    public Page<ProductEntities> getProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductEntities saveFromDto(ProductDto dto) {
        ProductEntities product = ProductEntities.builder()
                .fv_nombre(dto.getFv_nombre())
                .fv_descripcion(dto.getFv_descripcion())
                .fv_marca(dto.getFv_marca())
                .fn_precio(dto.getFn_precio())
                .fi_stock(dto.getFi_stock())
                .build();
        return productRepository.save(product);
    }

    @Override
    public ProductEntities updateFromDto(Long id, ProductDto dto) {
        //verificamos que el producto exista
        ProductEntities productoExistente = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("No se puede actualizar, producto no encontrado con id: " + id));

        //actualizamos los campos con la información del DTO
        productoExistente.setFv_nombre(dto.getFv_nombre());
        productoExistente.setFv_descripcion(dto.getFv_descripcion());
        productoExistente.setFv_marca(dto.getFv_marca());
        productoExistente.setFn_precio(dto.getFn_precio());
        productoExistente.setFi_stock(dto.getFi_stock());

        //guardamos los cambios en el registro existente
        return productRepository.save(productoExistente);
    }
}
