package com.blaise2s.packagemaster.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blaise2s.packagemaster.persistence.PersistenceService;
import com.blaise2s.packagemaster.utilities.Format;

@Path("/units")
public class UnitRestResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnits() {
		return Format.responseNoCache(PersistenceService.search("Unit.findAll", List.class));
	}

}
