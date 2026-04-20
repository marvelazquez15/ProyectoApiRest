package com.springboot.proyecto.ApiRest.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    //ERRORES DE VALIDACIÓN ej. lista vacia de productos
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
	    Map<String, Object> respuesta = new HashMap<>();
	    
	    //recolectar los errores, por si fallan varios campos)
	    String mensajeLimpio = ex.getBindingResult().getFieldErrors()
	            .stream()
	            .map(error -> error.getField() + ": " + error.getDefaultMessage())
	            .findFirst().orElse("Error de validación");

	    respuesta.put("status", HttpStatus.BAD_REQUEST.value());
	    respuesta.put("error", "Error de Validación");
	    respuesta.put("mensaje", mensajeLimpio);
	    return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	}

    //ERRORES DE NEGOCIO ej, stock insuficiente
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, Object>> handleServiceException(ServiceException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("error", "Error de Negocio");
        respuesta.put("mensaje", ex.getMessage());
        
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    //ERRORES GENÉRICOS
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        respuesta.put("error", "Error Interno del Servidor");
        respuesta.put("mensaje", "Ocurrió un error inesperado: " + ex.getMessage());
        
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
