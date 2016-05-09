package com.mim.planetas;

import com.mim.planetas.model.Planeta;
import com.mim.planetas.service.PlanetaService;

public class Main {

	/*
	 *  ● El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
		  horario. Su distancia con respecto al sol es de 500Km.
		● El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
		  horario. Su distancia con respecto al sol es de 2000Km.
		● El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
		  antihorario, su distancia con respecto al sol es de 1000Km.
		● Todas las órbitas son circulares.
	 */
	
	public static void main(String[] args) {

		Planeta Ferengi   = new Planeta("Ferengi"  , 1D, 500D);
		Planeta Betasoide = new Planeta("Betasoide", 3D, 2000D);
		Planeta Vulcano   = new Planeta("Vulcano"  , -5D, 1000D);
		
		int anios = 10 ;
		int dias = 360 * anios; // Se toman el año del planeta Ferengi

		PlanetaService.calcular(dias, Ferengi, Betasoide, Vulcano);
	}

}
