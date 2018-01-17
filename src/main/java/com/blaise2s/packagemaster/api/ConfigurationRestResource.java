package com.blaise2s.packagemaster.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blaise2s.packagemaster.model.Configuration;
import com.blaise2s.packagemaster.persistence.PersistenceService;
import com.blaise2s.packagemaster.utilities.Format;

@Path("/config")
public class ConfigurationRestResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConfigurations() {
		return Format.responseNoCache(PersistenceService.search("Configuration.findAll", Configuration.class));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createConfiguration(Configuration config) {
		return Format.responseNoCache(PersistenceService.persist(config));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateConfiguration(Configuration config) {
		return Format.responseNoCache(PersistenceService.update(config));
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteConfiguration(Configuration config) {
		return Format.responseNoCache(PersistenceService.delete(config));
	}

}
