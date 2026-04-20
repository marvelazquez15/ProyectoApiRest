package com.springboot.proyecto.ApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.proyecto.ApiRest.entities.OrderDetailEntities;

@Repository
public interface OrderDetailRepository extends JpaRepository <OrderDetailEntities, Long>{

}
