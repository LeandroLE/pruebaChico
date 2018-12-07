package com.prueba.nosolosoft.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.prueba.nosolosoft.dao.jpa.HotelRepository;
import com.prueba.nosolosoft.entities.Hotel;

/**
 * Servicio genérico para hoteles.
 * @author leandro.latorre
 *
 */
@Service
public class HotelService {
	
	private static final Logger log = LoggerFactory.getLogger(HotelService.class);

    @Autowired
    private HotelRepository hotelRepository;
    
    public HotelService() {}
    
    @SuppressWarnings("unchecked")
	public Page<Hotel> getAllHotels(Integer page, Integer size) {
    	log.info("Realizamos la búsqueda de todos los hoteles en el servicio de hoteles.");
        Page<Hotel> pageOfHotels = hotelRepository.findAll(PageRequest.of(page, size));
        return pageOfHotels;
    }
    
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    
    public Hotel getHotel(Integer id) {
        return hotelRepository.findById(id).get();
    }
}
