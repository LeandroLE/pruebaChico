package com.prueba.nosolosoft.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value="/prueba/hotels")
@Api(tags = {"hotels"})
public class HotelsController extends AbstractRestHandler {
	
	private static final Logger log = LoggerFactory.getLogger(HotelService.class);
	
    @Autowired
    private HotelService hotelService;
    
    /**
     * Método para crear un hotel nuevo.
     * @param hotel
     * @param request
     * @param response
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {"application/json" }, produces = {"application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Crea un hotel.", notes = "Devuelve la URL del hotel creado en Location header.")
    public void createHotel(@RequestBody Hotel hotel,
    		HttpServletRequest request, HttpServletResponse response) {
    	Hotel createdHotel = this.hotelService.createHotel(hotel);
    	response.setHeader("Location", request.getRequestURL().append("/").append(createdHotel.getId()).toString());
    }
    
	/**
	 * Método para la obtención (paginada) de la lista completa de hoteles.
	 * @param page Página que nos devuelva
	 * @param size Registros por página
	 * @param request
	 * @param response
	 * @return Página con el listado de hoteles
	 */
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
	
    /**
     * Método para obtener un hotel por ID.
     * @param id
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json" })
    @ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtención de un hotel por id.", notes = "Hay que pasar un ID válido.")
	@ResponseBody
	public Hotel getHotel(@ApiParam(value = "ID del hotel.", required = true) @PathVariable("id") Integer id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Hotel hotel = this.hotelService.getHotel(id);
		checkResourceFound(hotel);
        return hotel;
    }
}
