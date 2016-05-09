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
import org.springframework.beans.factory.annotation.Autowired;

import com.mim.planetas.service.MailService;

@Path("/cron")
@Produces(MediaType.APPLICATION_JSON)
public class CronResource {

	final static Logger logger = LoggerFactory.getLogger(CronResource.class);


	@Autowired
	private MailService mailService;

	@GET
	public Response cron() {
		logger.info("test cron");
		mailService.send();
		return Response.ok().build();
	}

}