package com.mim.planetas.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mim.planetas.dao.EMF;
import com.mim.planetas.model.Planeta;


@Path("/planeta")
@Produces(MediaType.APPLICATION_JSON)
public class PlanetaResource {

	final static Logger logger = LoggerFactory.getLogger(PlanetaResource.class);

	
	@GET
	@Path("/add/{planeta}/{velocidad}/{distancia}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(
			@PathParam("planeta") String planeta,
			@PathParam("velocidad") Double velocidad,
			@PathParam("distancia") Double distancia) {
		
		logger.info("add planeta");

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(new Planeta(planeta,velocidad, distancia));
		em.getTransaction().commit();
		em.close();

		return Response.ok().build();

	}

	
	
	@GET
	@Path("/init")
	@Produces(MediaType.APPLICATION_JSON)
	public Response init() {

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(new Planeta("Ferengi",1D, 500D));
		em.persist(new Planeta("Betasoide",3D, 2000D));
		em.persist(new Planeta("Vulcano",5D, 1000D));
		
		em.getTransaction().commit();
		em.close();

		return Response.ok().build();

	}
	
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response planetas() {

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Planeta> result = em.createQuery("SELECT p FROM Planeta p").getResultList();
		em.getTransaction().commit();
		em.close();

		return Response.ok(result).build();

	}
	
}