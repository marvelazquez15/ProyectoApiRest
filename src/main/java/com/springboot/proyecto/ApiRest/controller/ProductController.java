package com.springboot.proyecto.ApiRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.proyecto.ApiRest.dto.ProductDto;
import com.springboot.proyecto.ApiRest.entities.ProductEntities;
import com.springboot.proyecto.ApiRest.exceptions.ServiceException;
import com.springboot.proyecto.ApiRest.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping
    //Endpoint: GET /api/products - Obtener la lista de productos paginada.
    public ResponseEntity<Page<ProductDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntities> productos = productService.getProduct(pageable);
        
        //mapeo de Entity a DTO
        Page<ProductDto> dtos = productos.map(p -> {
            ProductDto dto = new ProductDto();
            dto.setFs_id(p.getFs_id());
            dto.setFv_nombre(p.getFv_nombre());
            dto.setFv_descripcion(p.getFv_descripcion());
            dto.setFv_marca(p.getFv_marca());
            dto.setFn_precio(p.getFn_precio());
            dto.setFi_stock(p.getFi_stock());
            return dto;
        });
        
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    // Endpoint: POST /api/products - Registrar un nuevo producto en el catálogo
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto productDto) {
        //el Service recibe el DTO y lo guarda como Entidad
        ProductEntities guardado = productService.saveFromDto(productDto);
        
        //se construye la respuesta de vuelta como DTO
        ProductDto respuesta = new ProductDto();
        respuesta.setFs_id(guardado.getFs_id());
        respuesta.setFv_nombre(guardado.getFv_nombre());
        respuesta.setFv_descripcion(guardado.getFv_descripcion());
        respuesta.setFv_marca(guardado.getFv_marca());
        respuesta.setFn_precio(guardado.getFn_precio());
        respuesta.setFi_stock(guardado.getFi_stock());
        
        //devuelve el DTO con estado Created
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    // Endpoint: PUT /api/products/{id} - Actualizar los datos de un producto existente.
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        //se manda el ID y el DTO al servicio para actualizar
        ProductEntities actualizado = productService.updateFromDto(id, productDto);
        
        //se construye la respuesta de vuelta como DTO
        ProductDto respuesta = new ProductDto();
        respuesta.setFs_id(actualizado.getFs_id());
        respuesta.setFv_nombre(actualizado.getFv_nombre());
        respuesta.setFv_descripcion(actualizado.getFv_descripcion());
        respuesta.setFv_marca(actualizado.getFv_marca());
        respuesta.setFn_precio(actualizado.getFn_precio());
        respuesta.setFi_stock(actualizado.getFi_stock());
        
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/{id}")
    // Endpoint: GET /api/products/{id} - Buscar un producto específico por su ID.
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductEntities p = productService.findById(id);
        ProductDto dto = new ProductDto();
        dto.setFs_id(p.getFs_id());
        dto.setFv_nombre(p.getFv_nombre());
        dto.setFv_descripcion(p.getFv_descripcion());
        dto.setFv_marca(p.getFv_marca());
        dto.setFn_precio(p.getFn_precio());
        dto.setFi_stock(p.getFi_stock());
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/import") 
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            //se cargo el archivo
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Subir un archivo .xlsx");
            }
            //lógica del servicio.
            productService.importProductsFromExcel(file);
            //se cargo correctamente 
            return ResponseEntity.ok("Productos cargados en la BD");
        } catch (ServiceException e) {
            //si lanzó un error devolvemos error 
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            //devolvemos error 500.
            return ResponseEntity.internalServerError().body("Error inesperado: " + e.getMessage());
        }
    }
}