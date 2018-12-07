package com.prueba.nosolosoft.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prueba.nosolosoft.exceptions.ResourceNotFoundException;

/**
 * Clase de la que extiendan los controllers. 
 * Contiene funcionalidad común para la API REST
 * @author leandro.latorre
 *
 */
public class AbstractRestHandler {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	  
    protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";
    
    
    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("recurso no encontrado");
        }
        return resource;
    }
}
