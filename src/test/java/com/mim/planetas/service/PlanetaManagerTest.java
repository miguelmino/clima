package com.mim.planetas.service;
import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import org.junit.Test;

import com.mim.planetas.model.Punto;
import com.mim.planetas.service.PlanetaService;

public class PlanetaManagerTest implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4421187516292059740L;
	
	/**
	 * Distancia entre dos puntos
	 * 
	 */
	@Test
	public void distanciaEntrePuntos() {

		Punto a = new Punto(1D, 1D);
		Punto b = new Punto(4D, 5D);
		Double distancia = PlanetaService.distancia(a, b);
		assertEquals(" Distancia entre (1,1) (4,5)", new Double(5), distancia);
	}
	
	/**
	 * Validacion de dos puntos en linea (ejemplo 1)
	 */
	@Test
	public void puntosEnLinea() {
		Punto a = new Punto(1D, 1D);
		Punto b = new Punto(4D, 4D);
		Punto c = new Punto(5D, 5D);
		Boolean ok = PlanetaService.puntosEnLinea(a, b, c);
		assertEquals("Puntos en linea recta x=y", Boolean.TRUE, ok);
	}
	
	/**
	 * Validacion de dos puntos en linea (ejemplo 2)
	 */
	@Test
	public void puntosEnLinea2() {
		Punto a = new Punto(1D, 2D);
		Punto b = new Punto(4D, 8D);
		Punto c = new Punto(5D, 10D);
		Boolean ok = PlanetaService.puntosEnLinea(a, b, c);
		assertEquals("Puntos en linea recta x=y", Boolean.TRUE, ok);
	}
	
	/**
	 * Validacion de puntos en Linea FALSO
	 * 
	 */
	@Test
	public void puntosEnLineaFalso() {

		Punto a = new Punto(1D, 2D);
		Punto b = new Punto(4D, 8D);
		Punto c = new Punto(6D, 5D);
		Boolean ok = PlanetaService.puntosEnLinea(a, b, c);
		assertEquals("Puntos en linea recta x=y", Boolean.FALSE, ok);
	}
	
	/**
	 * Puntos en 180 grados
	 */
	@Test
	public void puntosEnLinea180() {

		Punto a = new Punto(0D, 0D);
		Punto b = new Punto(1D, 0D);
		Punto c = new Punto(2D, 0D);
		Boolean ok = PlanetaService.puntosEnLinea(a, b, c);
		assertEquals("Puntos en linea recta x=y", Boolean.TRUE, ok);
	}
	
	/**
	 * Test Planetas en linea con el sol
	 */
	@Test
	public void planetasEnLineaConSol() {

		Punto a = new Punto(0D, 1D);
		Punto b = new Punto(-1D, -1D);
		Punto c = new Punto(1D, -1D);
		Boolean ok = PlanetaService.planetasEnLineaConSol(a, b, c);
		assertEquals("Puntos en linea recta x=y", Boolean.TRUE, ok);

	}
	
	/**
	 * Planetas En Linea Con el Sol FALSE
	 */
	@Test
	public void planetasEnLineaConSolFALSE() {

		Punto a = new Punto(0D, 1D);
		Punto b = new Punto(-1D, -1D);
		Punto c = new Punto(-1D, 1D);
		Boolean ok = PlanetaService.planetasEnLineaConSol(a, b, c);
		assertEquals("Puntos en linea recta x=y", Boolean.FALSE, ok);
	}
	
	/**
	 * Se valida la obtencion del punto a partir de los datos del planeta creado
	 * 
	 * Datos : 45 Grados Hipotenusa Raiz 8 
	 * 
	 * Valor Esperado 2
	 */
	@Test
	public void getPunto() {

		Punto p = PlanetaService.getPunto(45, 1D, Math.sqrt(8));
		assertEquals("Puntos en linea recta x=y", new Double(2), new Double(Math.round(p.getX()*100)/100.0));

	}
	

	
}
