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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.proyecto.ApiRest.dto.UserDto;
import com.springboot.proyecto.ApiRest.entities.UserEntities;
import com.springboot.proyecto.ApiRest.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    // Endpoint: GET /api/users - Listar todos los usuarios registrados con soporte de paginación.
    public ResponseEntity<Page<UserDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntities> users = userService.findAll(pageable);
        
        return ResponseEntity.ok(users.map(this::convertToDto));
    }

    @GetMapping("/{id}")
    // Endpoint: GET /api/users/{id} - Buscar un usuario específico por su ID.
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserEntities user = userService.findById(id);
        return ResponseEntity.ok(convertToDto(user));
    }

    @PostMapping
    // Endpoint: POST /api/users - Crear un nuevo usuario en la base de datos.
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        UserEntities guardado = userService.saveFromDto(userDto);
        return new ResponseEntity<>(convertToDto(guardado), HttpStatus.CREATED);
    }

    //método de apoyo para mapeo de Entity a DTO
    private UserDto convertToDto(UserEntities user) {
        UserDto dto = new UserDto();
        dto.setFs_id(user.getFs_id());
        dto.setFv_nombre(user.getFv_nombre());
        dto.setFv_apellido_paterno(user.getFv_apellido_paterno());
        dto.setFv_apellido_materno(user.getFv_apellido_materno());
        dto.setFv_email(user.getFv_email());
        dto.setFn_telefono(user.getFn_telefono());
        return dto;
    }
}