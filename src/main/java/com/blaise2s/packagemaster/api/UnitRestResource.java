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

import com.blaise2s.packagemaster.model.Unit;
import com.blaise2s.packagemaster.persistence.PersistenceService;
import com.blaise2s.packagemaster.utilities.Format;

@Path("/units")
public class UnitRestResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnits() {
		return Format.responseNoCache(PersistenceService.search("Unit.findAll", Unit.class));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUnit(Unit u) {
		return Format.responseNoCache(PersistenceService.persist(u));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUnit(Unit u) {
		return Format.responseNoCache(PersistenceService.update(u));
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUnit(Unit u) {
		return Format.responseNoCache(PersistenceService.delete(u));
	}

}
