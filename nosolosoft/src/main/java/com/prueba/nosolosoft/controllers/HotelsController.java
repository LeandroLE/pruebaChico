package com.prueba.nosolosoft.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.nosolosoft.entities.Hotel;
import com.prueba.nosolosoft.services.HotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Clase controladora general para Hoteles (única).
 * @author leandro.latorre
 *
 */
@RestController
@RequestMapping(value="/hotels")
@Api(tags = {"hotels"})
public class HotelsController extends AbstractRestHandler {
	
	private static final Logger log = LoggerFactory.getLogger(HotelService.class);
	
    @Autowired
    private HotelService hotelService;
    
    
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtiene una lista con todos los hoteles.", 
		notes = "Lista paginada. Puedes pasar el número de página por parámetro (default 0) y el tamaño (default 100)")
	@ResponseBody
	public Page<Hotel> getAllHotel(@ApiParam(value = "El número de página", required = true)
			@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
			@ApiParam(value = "Número de registros de la página", required = true) 
			@RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("Método de HotelsController para la obtención del listado total de hoteles.");
		return this.hotelService.getAllHotels(page, size);
	}
	
	
	
}
