package com.blaise2s.packagemaster.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blaise2s.packagemaster.model.Resident;
import com.blaise2s.packagemaster.model.Unit;
import com.blaise2s.packagemaster.persistence.PersistenceService;
import com.blaise2s.packagemaster.utilities.Format;

@Path("/residents")
public class ResidentRestResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResidents() {
		return Format.responseNoCache(PersistenceService.search("Resident.findAll", Resident.class));
	}

	@GET
	@Path("/{unit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResident(@PathParam("unit") String unit) {
		return Format.responseNoCache(PersistenceService.search("Resident.findByUnit", Resident.class, unit));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createResident(Resident r) {
		Unit unit = PersistenceService.search(Unit.class, r.getUnit().getId());
		r.setUnit(unit);
		return Format.responseNoCache(PersistenceService.persist(r));
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateResident(Resident r) {
		return Format.responseNoCache(PersistenceService.update(r));
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteResident(@PathParam("id") String id) {
		return Format.responseNoCache(PersistenceService.delete(Resident.class, Integer.parseInt(id)));
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteResident(Resident r) {
		return Format.responseNoCache(PersistenceService.delete(r));
	}

}
