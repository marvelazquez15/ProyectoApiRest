package com.springboot.proyecto.ApiRest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.proyecto.ApiRest.entities.ProductEntities;  

@Repository
public interface ProductRepository extends JpaRepository<ProductEntities, Long>{
	Page<ProductEntities> findAll(Pageable pageable);
	@Query("SELECT COUNT(p) > 0 FROM ProductEntities p WHERE p.fv_nombre = ?1")
	boolean existsByFvNombre(String fvNombre); //metodo para saber si el producto ya existe 
}
