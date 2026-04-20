package com.springboot.proyecto.ApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.proyecto.ApiRest.entities.UserEntities;

@Repository 
public interface UserRepository extends JpaRepository<UserEntities, Long> {

}
