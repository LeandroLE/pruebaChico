package com.prueba.nosolosoft.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Disponibilidad de las habitaciones por día y hotel. La columna rooms marca la disponibilidad actualizada del hotel.
 * @author leandro.latorre
 *
 */
@Entity
@Data
public class Availability {

	@Id
	private Date date;
	private Integer hotel_id;
	private Integer	rooms;
	
	protected Availability() {}

	public Availability(Date date, Integer hotel_id, Integer rooms) {
		super();
		this.date = date;
		this.hotel_id = hotel_id;
		this.rooms = rooms;
	}
	
}
