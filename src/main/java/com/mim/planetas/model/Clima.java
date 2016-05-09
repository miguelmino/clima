package com.mim.planetas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mim.planetas.service.PlanetaService;

@Entity
@Table(name = "Clima")
public class Clima {

	private Long id;
	private Integer dia;
	private String descripcion;
	
	public Clima() {}
	
	

	public Clima(Integer dia, String descripcion) {
		super();
		this.dia = dia;
		this.descripcion = descripcion;
	}



	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
