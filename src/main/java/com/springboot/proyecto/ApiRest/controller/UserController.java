package com.springboot.proyecto.ApiRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        
        //mapeo de Entity a DTO
        Page<UserDto> dtos = users.map(user -> {
            UserDto dto = new UserDto();
            dto.setFs_id(user.getFs_id());
            dto.setFv_nombre(user.getFv_nombre());
            dto.setFv_apellido_paterno(user.getFv_apellido_paterno());
            dto.setFv_apellido_materno(user.getFv_apellido_materno());
            dto.setFv_email(user.getFv_email());
            dto.setFn_telefono(user.getFn_telefono());
            return dto;
        });
        
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    // Endpoint: POST /api/users - Crear un nuevo usuario en la base de datos.
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        UserEntities guardado = userService.saveFromDto(userDto);
        
        UserDto respuesta = new UserDto();
        respuesta.setFs_id(guardado.getFs_id());
        respuesta.setFv_nombre(guardado.getFv_nombre());
        respuesta.setFv_apellido_paterno(guardado.getFv_apellido_paterno());
        respuesta.setFv_apellido_materno(guardado.getFv_apellido_materno());
        respuesta.setFv_email(guardado.getFv_email());
        respuesta.setFn_telefono(guardado.getFn_telefono());
        
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}