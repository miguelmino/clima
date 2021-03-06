package com.mim.planetas.rest;

import java.io.IOException;
import java.io.Serializable;
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

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.mim.planetas.dao.EMF;
import com.mim.planetas.model.Clima;
import com.mim.planetas.model.Planeta;
import com.mim.planetas.model.Solucion;
import com.mim.planetas.service.PlanetaService;

/**
 * http://www.programcreek.com/java-api-examples/index.php?source_dir=appengine-java-vm-runtime-master/testwebapp/src/main/java/com/google/apphosting/tests/usercode/testservlets/InitServlet.java
 * @author mmiño
 *
 */
@Path("/clima")
@Produces(MediaType.APPLICATION_JSON)
public class ClimaResource implements Serializable {

	private static final long serialVersionUID = -2359551600046025138L;

	final static Logger logger = LoggerFactory.getLogger(ClimaResource.class);

	
	
	/**
	 * Ej: GET → http://....../clima?dia=566 → Respuesta: {“dia”:566,
	 * “clima”:”lluvia”}
	 * 
	 * @param dia
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response planetas(@QueryParam("dia") Integer dia) {
		
		String select = "SELECT c FROM Clima c where c.dia=:dia";
		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Clima> result = em.createQuery(select)
							   .setParameter("dia",dia).getResultList();
		em.getTransaction().commit();
		em.close();

		Map<String, String> response = new HashMap<String, String>();
		response.put("dia", dia.toString());
		response.put("clima", result.get(0).getDescripcion());
		
		return Response.ok(response).build();

	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/calcular")
	@Produces(MediaType.APPLICATION_JSON)
	public Response calcular() {

		Integer anios =  10;
		Integer dias  = 360 * anios; // Se toman el año del planeta Ferengi

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Planeta> result = em.createQuery("SELECT p FROM Planeta p").getResultList();
		em.getTransaction().commit();
		em.close();
		List<Clima> climas = null ;
		Solucion solucion = PlanetaService.calcular(dias, Boolean.FALSE, result);

		return Response.ok(solucion).build();

	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Path("/engine")
	@Produces(MediaType.APPLICATION_JSON)
	public Response job() {

		Integer anios =  10;
		Integer dias  = 360 * anios; // Se toman el año del planeta Ferengi

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Planeta> result = em.createQuery("SELECT p FROM Planeta p").getResultList();
		em.getTransaction().commit();


		Solucion solucion = PlanetaService.calcular(dias,  Boolean.TRUE, result);

		em.getTransaction().begin();
		
		for(Clima clima : solucion.getClimas()){
			em.persist(clima);
		}
		
		em.getTransaction().commit();
		em.close();
		
		return Response.ok(solucion).build();
	}
	

	/**
	 * 
	 * @return
	 */

	@GET
	@Path("/climas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response planetas() {

		EntityManagerFactory emf = EMF.get();
		EntityManager em = emf.createEntityManager();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Clima> result = em.createQuery("SELECT c FROM Clima c").getResultList();
		em.getTransaction().commit();
		em.close();

		return Response.ok(result).build();

	}
	
	/**
	 * ejecucion de Tarea
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/task")
	@Produces(MediaType.APPLICATION_JSON)
	public Response task() throws IOException {

		Queue q = QueueFactory.getDefaultQueue();
		TaskOptions.Method method = TaskOptions.Method.GET;
		String url = "/clima/engine";
		TaskOptions opts = TaskOptions.Builder.withUrl(url).method(method);
		TaskHandle handle = q.add(opts);
		return Response.ok().build();
	}

}