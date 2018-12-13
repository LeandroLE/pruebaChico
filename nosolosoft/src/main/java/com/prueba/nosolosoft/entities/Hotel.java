package com.prueba.nosolosoft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Datos de los hoteles.
 * @author leandro.latorre
 *
 */
@Entity
@Data
@Table(name="Hotels")
public class Hotel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer category;
	
	protected Hotel() {}

	public Hotel(String name, Integer category) {
		super();
		this.name = name;
		this.category = category;
	}
	
}
