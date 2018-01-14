package com.blaise2s.packagemaster.api;

import java.io.IOException;

import javax.ws.rs.Consumes;
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
import com.blaise2s.packagemaster.utilities.Format;
import com.twilio.rest.api.v2010.account.Message.Status;

@Path("/delivery")
public class DeliveryRestResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postDelivery(Delivery delivery) throws IOException {
		Resident resident = delivery.getRecipient();
		int numPackages = delivery.getNumPackages();
		int sendGridStatusCode = SendGridMailer.sendMail(resident.getEmail(), numPackages);
		Status twillioStatus = TwilioMessanger.sendText(resident.getPhone(), numPackages);
		return Format.responseNoCache(new NotificationStatus(sendGridStatusCode, twillioStatus));
	}

}
