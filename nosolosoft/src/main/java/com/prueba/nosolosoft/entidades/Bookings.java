package com.prueba.nosolosoft.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Bookings {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer category;
	
	protected Bookings() {}

	public Bookings(String name, Integer category) {
		super();
		this.name = name;
		this.category = category;
	}
	
	
}
