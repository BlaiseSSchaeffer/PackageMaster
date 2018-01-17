package com.blaise2s.packagemaster.api;

import java.util.Date;

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

import com.blaise2s.packagemaster.model.Delivery;
import com.blaise2s.packagemaster.model.NotificationStatus;
import com.blaise2s.packagemaster.model.Resident;
import com.blaise2s.packagemaster.notification.SendGridMailer;
import com.blaise2s.packagemaster.notification.TwilioMessanger;
import com.blaise2s.packagemaster.persistence.PersistenceService;
import com.blaise2s.packagemaster.utilities.Format;

@Path("/deliveries")
public class DeliveryRestResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeliveries() {
		return Format.responseNoCache(PersistenceService.search("Delivery.findAll", Delivery.class));
	}

	@GET
	@Path("/resident/{residentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeliveriesByResident(@PathParam("residentId") Integer residentId) {
		return Format
				.responseNoCache(PersistenceService.search("Delivery.findByResidentId", Resident.class, residentId));
	}

	@GET
	@Path("/unit/{unitId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeliveriesByUnit(@PathParam("unitId") String unitId) {
		return Format.responseNoCache(PersistenceService.search("Delivery.findByUnitId", Resident.class, unitId));
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDelivery(Delivery delivery) {
		int residentId = delivery.getResidentId();
		int numPackages = delivery.getPackages();
		Resident resident = PersistenceService.search(Resident.class, residentId);
		delivery.setUnitId(resident.getUnit().getId());

		String twillioStatus = TwilioMessanger.sendText(resident.getPhone(), numPackages);
		int sendGridStatusCode = SendGridMailer.sendMail(resident.getEmail(), numPackages);

		NotificationStatus ns = new NotificationStatus();
		ns.setTwillioStatus(twillioStatus);
		ns.setSendGridStatusCode(sendGridStatusCode);
		Integer id = (Integer) PersistenceService.persist(ns, NotificationStatus.class);
		ns.setId(id);
		delivery.setNotificationStatus(ns);

		delivery.setNotified(new Date());
		PersistenceService.persist(delivery);

		resident.getDeliveries().add(delivery);
		PersistenceService.update(resident);

		return Format.responseNoCache(ns);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDelivery(Delivery d) {
		return Format.responseNoCache(PersistenceService.update(d));
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDelivery(Delivery d) {
		return Format.responseNoCache(PersistenceService.delete(d));
	}

}
