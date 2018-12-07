package com.prueba.nosolosoft.entities;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Availability {

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
