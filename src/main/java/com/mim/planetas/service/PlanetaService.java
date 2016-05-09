package com.mim.planetas.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import com.mim.planetas.dao.EMF;
import com.mim.planetas.model.Clima;
import com.mim.planetas.model.Planeta;
import com.mim.planetas.model.Punto;

@Service
public class PlanetaService {

	//	Double a = null,b = null,c = null;
	//	Double s = 0.5 * (a + b + c);
	//	Double area = Math.sqrt(s * (s-a) *(s-b) * (s-c));
		
	public static Punto getPunto(Integer dia, Double velocidad,Double distancia) {
		Double v = dia * velocidad * (2 * Math.PI) / 360;
		Double x = Math.cos(v) * distancia;
		Double y = Math.sin(v) * distancia;
		return new Punto(Math.round(x*100)/100.0,Math.round(y*100)/100.0);
	}
	
	/**
	 * Se valida a traves de la ecuacion de la recta la pertencia de p0
	 * en la ecuacion formada por los puntos p1 y p2
	 * 
	 */
	
	public static Boolean planetasEnLinea(Planeta p0, Planeta p1, Planeta p2) {
		return puntosEnLinea(p0.punto(), p1.punto(), p2.punto());
	}
	
	/**
	 * 
	 * @param p0
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static Boolean puntosEnLinea(Punto p0, Punto p1, Punto p2) {
		Double ecu1 = (p0.getX() - p1.getX()) * (p2.getY() - p1.getY());
		Double ecu2 = (p0.getY() - p1.getY()) * (p2.getX() - p1.getX());
		return (Math.round(ecu1.doubleValue()*100)/100.0) == (Math.round(ecu2.doubleValue()*100)/100.0);
	}
	
	/**
	 * Si los tres planetas estan en linea con el sol la diferencia debe ser 
	 * igual a 0 o 180 entre ambas
	 */
	public static Boolean planetasEnLineaConSol(Double dif1 ,Double dif2 , Double dif3 ){
		
		return ( dif1.doubleValue() == 0 || dif1.doubleValue() == 180 ) && ( dif2.doubleValue() == 0 || dif2.doubleValue() == 180 );
	}

	/**
	 * Calculo del perimetro entre los planetas
	 * 
	 * @param ferengi
	 * @param betasoide
	 * @param vulcano
	 * @return
	 */
	public static Double perimetroEntrePlanetas(Planeta ferengi, Planeta betasoide, Planeta vulcano) {

		return  distancia(ferengi.punto()  ,betasoide.punto()) + 
				distancia(betasoide.punto(),vulcano.punto()  ) + 
				distancia(vulcano.punto()  ,ferengi.punto()  ) ;
	}
	
	public static Double distancia(Punto a ,Punto b){
		Double distancia =  Math.pow(a.getX()- b.getX(),2)  + Math.pow(a.getY()- b.getY(),2) ;
		return Math.sqrt(distancia) ;
	}
	
	/**
	 * La orientación de un triángulo es la misma que la orientación de sus tres vértices, 
	 * así que se puede establecer un algoritmo sencillo para decidir si un punto está o 
	 * no en el interior de un triángulo.
	 * fuente : 
	 * http://www.dma.fi.upm.es/personal/mabellanas/tfcs/kirkpatrick/Aplicacion/algoritmos.htm#puntoInterior
	 * (A1.x - A3.x) * (A2.y - A3.y) - (A1.y - A3.y) * (A2.x - A3.x)
	 * @return
	 */
	public static Double orientacion(Punto A1, Punto A2, Punto A3){
		Double o =  (A1.getX() - A3.getX()) * (A2.getY() - A3.getY()) - (A1.getY() - A3.getY()) * (A2.getX() - A3.getX());
		return (Math.round(o.doubleValue()*100)/100.0);
		
	}
	
	public static Boolean planetasRodeandoElSol(Planeta ferengi, Planeta betasoide, Planeta vulcano ){
		return planetasEnLineaConSol(ferengi.punto(),betasoide.punto(), vulcano.punto());
	}
		
		
	public static Boolean planetasEnLineaConSol(Punto p0, Punto p1, Punto p2){
		
		Punto cerocero = new Punto(0D,0D);
		Double o0 = orientacion(p0, p1, p2);
		Double o1 = orientacion(p0, p1, cerocero);
		Double o2 = orientacion(p1, p2, cerocero);
		Double o3 = orientacion(p2, p0, cerocero);

		return ((o0>0 && o1>0 && o2>0 && o3>0) || (o0<0 && o1<0 && o2<0 && o3<0))? true:false;

	}
		
	public static Map calcular(Integer dias ,Planeta Ferengi, Planeta Betasoide, Planeta Vulcano){
		
		Integer cantPeriodosDeSequia = 0 ;
		Integer cantPeriodosDeLluvia = 0 ;
		Double  picoMaximoDeLluvia   = 0D;
		Integer cantPeriodosOptimos  = 0 ;
		
		
		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		
		
		
		for(int dia=0 ; dia < dias; dia++){
			String clima= "lluvia";
			Ferengi.setDia(dia);
			Betasoide.setDia(dia);
			Vulcano.setDia(dia);
			
			Double dif1 = Ferengi.diferenciaAngular(Betasoide);
			Double dif2 = Ferengi.diferenciaAngular(Vulcano);
			Double dif3 = Betasoide.diferenciaAngular(Vulcano);

//			1. ¿Cuántos períodos de sequía habrá?
//			2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
//			3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?
					
			if(PlanetaService.planetasEnLineaConSol(dif1, dif2, dif3)){
				clima="sequia";
				cantPeriodosDeSequia++;
				
			}

			if(PlanetaService.planetasEnLinea(Ferengi, Betasoide, Vulcano)){
				clima="optimo";
				System.out.println(String.format("CONDICIONES ÓPTIMAS %s", dia));
				cantPeriodosOptimos++;
			}
			
			if(PlanetaService.planetasRodeandoElSol(Ferengi,Betasoide,Vulcano)){
				
				cantPeriodosDeLluvia++;
				Double perimetro  = PlanetaService.perimetroEntrePlanetas(Ferengi, Betasoide, Vulcano);
				if(picoMaximoDeLluvia < perimetro ){
					//System.out.println(String.format("PERIMERTRO ENTRE LOS PLANETAS %1.2f %1.2f ", picoMaximoDeLluvia,perimetro));
					picoMaximoDeLluvia = perimetro;
				}
			}

			em.getTransaction().begin();
			em.persist(new Clima(dia,clima));
			em.getTransaction().commit();


		}
		
		
		em.close();
		
		Map<String,Object> response = new HashMap<String,Object> ();
		
		response.put("cantPeriodosDeSequia", cantPeriodosDeSequia);
		response.put("cantPeriodosDeLluvia", cantPeriodosDeLluvia);
		response.put("picoMaximoDeLluvia", picoMaximoDeLluvia);
		response.put("cantPeriodosOptimos", cantPeriodosOptimos );
		
		return response ;
	}
	
	
}
