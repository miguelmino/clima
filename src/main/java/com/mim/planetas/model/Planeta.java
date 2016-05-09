package com.mim.planetas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mim.planetas.service.PlanetaService;

@Entity
@Table(name = "Planeta")
public class Planeta {

	private Long id;
	private String name;
	private Integer dia;
	private Double velocidad;
	private Double distancia;

	public Planeta() {
	  }

	public Planeta(String name, Double velocidad, Double distancia) {
		super();
		this.name = name;
		this.velocidad = velocidad;
		this.distancia = distancia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Double velocidad) {
		this.velocidad = velocidad;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Planeta(String name, Double velocidad) {
		super();
		this.name = name;
		this.velocidad = velocidad;
	}

	public Double diferenciaAngular(Planeta betasoide) {
		return (Math.abs(this.velocidad * dia - betasoide.getVelocidad() * dia)) % 360;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Punto punto() {
		return PlanetaService.getPunto(dia, velocidad, distancia);
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

	// public Punto getPunto(Integer dia, Double velocidad) {
	// Double v = dia * velocidad * (2 * Math.PI) / 360;
	// Double x = Math.cos(v) * distancia;
	// Double y = Math.sin(v) * distancia;
	// return new Punto(x, y);
	// }

	// public Boolean isRecta(Planeta p1, Planeta p2) {
	// return isRecta(this.getPunto(), p1.getPunto(), p2.getPunto());
	// }
	//
	// public Boolean isRecta(Punto p0, Punto p1, Punto p2) {
	// Double ecu1 = (p0.getX() - p1.getX()) * (p2.getY() - p1.getY());
	// Double ecu2 = (p0.getY() - p1.getY()) * (p2.getX() - p1.getX());
	// return ecu1.doubleValue() == ecu2.doubleValue();
	// }

}
