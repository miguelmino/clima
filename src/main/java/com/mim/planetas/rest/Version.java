package com.mim.planetas.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.appengine.api.taskqueue.TaskOptions;

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