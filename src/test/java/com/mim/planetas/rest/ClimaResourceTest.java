package com.mim.planetas.rest;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mim.planetas.TestConfig;
import com.mim.planetas.config.JerseyConfig;


public class ClimaResourceTest extends JerseyTest {


	@Override
    protected Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        return new JerseyConfig()  .property("contextConfig", context);
    }

	/**
	 * Ej: GET → http://....../clima?dia=566 → 
	 * Respuesta: {“dia”:566, “clima”:”lluvia”}
	 * 
	 */
	@Test
	public void testPlanetaInit() {

		//target("planeta/init").request().get();

	}
	
	
	@Test
	public void testPlanetaAll() {

		target("planeta/init").request().get();
		
		final Response response = target("planeta/all").request().get();
		List json = response.readEntity(List.class);
		
		assertEquals(3, json.size());

	}
	
	
	
	
//	assertEquals("566",map.get("dia"));
//	assertEquals("lluvia",map.get("clima"));
	
	
//	final Response response = target("clima")
//	.queryParam("dia", "0")
//	.request().get();

	
	
//    final String messages = target("messages").request().get(String.class);
//    String expected = "[ " +
//            "{ 'author': 'Joe', 'contents': 'Hello'}," +
//            "{ 'author': 'Jane', 'contents': 'Spring boot is cool !'}" +
//            "]";
//    
//    JSONAssert.assertEquals(expected, messages, JSONCompareMode.LENIENT);
    
    
}
