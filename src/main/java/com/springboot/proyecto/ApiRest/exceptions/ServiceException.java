package com.springboot.proyecto.ApiRest.exceptions;

public class ServiceException extends RuntimeException {
	public ServiceException(String mensaje) {
        super(mensaje);
    }
}
