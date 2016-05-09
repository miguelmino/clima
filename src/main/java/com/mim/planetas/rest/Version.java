package com.mim.planetas.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/version")
@Produces(MediaType.APPLICATION_JSON)
public class Version {

	final static Logger logger = LoggerFactory.getLogger(Version.class);

	@GET
	public Response test() {
		Map response = new HashMap();
		response.put("app.version", System.getProperty("app.version"));
		return Response.ok(response).build();
	}


}