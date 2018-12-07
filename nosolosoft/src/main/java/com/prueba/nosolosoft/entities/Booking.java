package com.prueba.nosolosoft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Bookings")
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer category;
	
	protected Booking() {}

	public Booking(String name, Integer category) {
		super();
		this.name = name;
		this.category = category;
	}
	
	
}
