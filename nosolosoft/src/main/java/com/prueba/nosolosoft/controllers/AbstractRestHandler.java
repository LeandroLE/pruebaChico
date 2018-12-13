package com.prueba.nosolosoft.controllers;

import java.util.Optional;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.prueba.nosolosoft.exceptions.ResourceNotFoundException;
import com.prueba.nosolosoft.pojos.RestErrorInfo;

/**
 * Clase de la que extiendan los controllers. 
 * Contiene funcionalidad común para la API REST y mapeo de excepciones.
 * @author leandro.latorre
 *
 */
public class AbstractRestHandler implements ApplicationEventPublisherAware  {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;

    protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public
    @ResponseBody
    RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        log.info("Respuesta de excepción: " + ex.getMessage());

        return new RestErrorInfo(ex, "Se ha producido un error de formato.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        log.info("ResourceNotFoundException handler:" + ex.getMessage());

        return new RestErrorInfo(ex, "Lo siento no ha sido encontrado.");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
    
    
    public static <T> T checkResourceFound(final Optional<T> resource) {
        if(!resource.isPresent()) {
        	throw new ResourceNotFoundException("Recurso no encontrado");
        }
        return resource.get();
    }
}
