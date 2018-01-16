package com.blaise2s.packagemaster.api;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	public Response getResidents() {
		return Format.responseNoCache(PersistenceService.search("Delivery.findAll", Delivery.class));
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postDelivery(Delivery delivery) {
		int residentId = delivery.getResidentId();
		int numPackages = delivery.getPackages();
		Resident resident = PersistenceService.search(Resident.class, residentId);

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

}
