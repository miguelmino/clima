package com.mim.planetas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mmiño
 *
 */
public class Solucion {

	private Integer cantPeriodosDeSequia;
	private Integer cantPeriodosDeLluvia;
	private Double picoMaximoDeLluvia;
	private Integer cantPeriodosOptimos;
	private List<Clima> climas = new ArrayList<Clima>();

	public Solucion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Solucion(Integer cantPeriodosDeSequia, Integer cantPeriodosDeLluvia, Double picoMaximoDeLluvia,
			Integer cantPeriodosOptimos, List<Clima> climas) {
		super();
		this.cantPeriodosDeSequia = cantPeriodosDeSequia;
		this.cantPeriodosDeLluvia = cantPeriodosDeLluvia;
		this.picoMaximoDeLluvia = picoMaximoDeLluvia;
		this.cantPeriodosOptimos = cantPeriodosOptimos;
		this.climas = climas;
	}

	public Integer getCantPeriodosDeSequia() {
		return cantPeriodosDeSequia;
	}

	public void setCantPeriodosDeSequia(Integer cantPeriodosDeSequia) {
		this.cantPeriodosDeSequia = cantPeriodosDeSequia;
	}

	public Integer getCantPeriodosDeLluvia() {
		return cantPeriodosDeLluvia;
	}

	public void setCantPeriodosDeLluvia(Integer cantPeriodosDeLluvia) {
		this.cantPeriodosDeLluvia = cantPeriodosDeLluvia;
	}

	public Double getPicoMaximoDeLluvia() {
		return picoMaximoDeLluvia;
	}

	public void setPicoMaximoDeLluvia(Double picoMaximoDeLluvia) {
		this.picoMaximoDeLluvia = picoMaximoDeLluvia;
	}

	public Integer getCantPeriodosOptimos() {
		return cantPeriodosOptimos;
	}

	public void setCantPeriodosOptimos(Integer cantPeriodosOptimos) {
		this.cantPeriodosOptimos = cantPeriodosOptimos;
	}

	public List<Clima> getClimas() {
		return climas;
	}

	public void setClimas(List<Clima> climas) {
		this.climas = climas;
	}

}
