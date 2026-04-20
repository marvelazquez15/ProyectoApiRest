package com.springboot.proyecto.ApiRest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.proyecto.ApiRest.dto.UserDto;
import com.springboot.proyecto.ApiRest.entities.UserEntities;

public interface UserService {
	Page<UserEntities> findAll(Pageable pageable);
	UserEntities saveFromDto(UserDto dto);
    UserEntities findById(Long id); 
    UserEntities save(UserEntities user); 
}
