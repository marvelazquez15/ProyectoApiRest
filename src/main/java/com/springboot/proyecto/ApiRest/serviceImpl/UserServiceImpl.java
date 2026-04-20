package com.springboot.proyecto.ApiRest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.proyecto.ApiRest.dto.UserDto;
import com.springboot.proyecto.ApiRest.entities.UserEntities;
import com.springboot.proyecto.ApiRest.exceptions.ServiceException;
import com.springboot.proyecto.ApiRest.repository.UserRepository;
import com.springboot.proyecto.ApiRest.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserEntities> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    //metodo para que el Service reciba el DTO
    public UserEntities saveFromDto(UserDto dto) {
        UserEntities user = UserEntities.builder()
                .fv_nombre(dto.getFv_nombre())
                .fv_apellido_paterno(dto.getFv_apellido_paterno())
                .fv_apellido_materno(dto.getFv_apellido_materno())
                .fv_email(dto.getFv_email())
                .fn_telefono(dto.getFn_telefono())
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserEntities findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Usuario no encontrado con id: " + id));
    }

    @Override
    public UserEntities save(UserEntities user) {
        return userRepository.save(user);
    }
}