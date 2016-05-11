package com.mim.planetas.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//[END simple_includes]

import com.mim.planetas.dao.EMF;
import com.mim.planetas.model.Clima;
import com.mim.planetas.model.Planeta;
import com.mim.planetas.service.PlanetaService;

@Path("/clima")
@Produces(MediaType.APPLICATION_JSON)
public class ClimaResource {

	final static Logger logger = LoggerFactory.getLogger(ClimaResource.class);


	/**
	 * Ej: GET → http://....../clima?dia=566 → Respuesta: {“dia”:566,
	 * “clima”:”lluvia”}
	 * 
	 * @param dia
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response planetas(@QueryParam("dia") Integer dia) {

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Clima> result = em.createQuery("SELECT c FROM Clima c where c.dia=:dia").setParameter("dia", dia)
				.getResultList();

		em.getTransaction().commit();
		em.close();

		Map<String, String> response = new HashMap<String, String>();
		response.put("dia", dia.toString());
		response.put("clima", result.get(0).getDescripcion());
		return Response.ok(response).build();

	}

	@GET
	@Path("/engine")
	@Produces(MediaType.APPLICATION_JSON)
	public Response engine(@QueryParam("dia") Integer dia) {

		int anios = 10;
		int dias = 360 * anios; // Se toman el año del planeta Ferengi

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Planeta> result = em.createQuery("SELECT p FROM Planeta p").getResultList();
		em.getTransaction().commit();
		em.close();

		Map response = PlanetaService.calcular(dias, result.get(0), result.get(1), result.get(2));

		return Response.ok(response).build();

	}

	@GET
	@Path("/climas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response planetas() {

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Clima> result = em.createQuery("SELECT p FROM Clima p").getResultList();
		em.getTransaction().commit();
		em.close();

		return Response.ok(result).build();

	}

}