package com.blaise2s.packagemaster.notification;

import java.io.IOException;

import com.blaise2s.packagemaster.config.Config;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class SendGridMailer {

	private SendGridMailer() {
		// Intentionally left empty.
	}

	public static int sendMail(String email, int numPackages) throws IOException {
		Email from = new Email(Config.SENDGRID_FROM_USER);
		String subject = Config.SENDGRID_EMAIL_SUBJECT;
		Content content = new Content(Config.SENDGRID_EMAIL_CONTENT_TYPE,
				String.format(Config.SENDGRID_EMAIL_CONTENT, numPackages));
		Mail mail = new Mail();
		mail.setFrom(from);
		mail.setSubject(subject);
		mail.addContent(content);
		Personalization personalization = new Personalization();
		personalization.addTo(new Email(email));
		mail.addPersonalization(personalization);
		SendGrid sg = new SendGrid(Config.SENDGRID_API_KEY);
		Request request = new Request();
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		Response response = sg.api(request);
		return response.getStatusCode();
	}
}
