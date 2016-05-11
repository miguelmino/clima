package com.mim.planetas.rest;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mim.planetas.TestConfig;
import com.mim.planetas.config.JerseyConfig;


public class VersionResourceTest extends JerseyTest {


	@Override
    protected Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        return new JerseyConfig()  .property("contextConfig", context);
    }
	
	
	@Test
	public void test() {
		final Response response = target("version").request().get();
		Map map = response.readEntity(Map.class);
		
		System.out.println(map.get("app.version"));
		
		assertEquals(System.getProperty("app.version"),map.get("app.version"));

	}
}
