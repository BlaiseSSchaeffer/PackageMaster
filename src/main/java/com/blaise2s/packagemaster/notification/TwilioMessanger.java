package com.blaise2s.packagemaster.notification;

import com.blaise2s.packagemaster.config.Config;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.Message.Status;
import com.twilio.type.PhoneNumber;

public class TwilioMessanger {

	private TwilioMessanger() {
		// Intentionally left empty.
	}

	public static Status sendText(String phone, int numPackages) {
		Twilio.init(Config.TWILIO_ACCOUNT_SID, Config.TWILIO_AUTH_TOKEN);

		Message m = Message.creator(new PhoneNumber(Config.TWILIO_COUNTRY_CODE + phone),
				new PhoneNumber(Config.TWILIO_COUNTRY_CODE + Config.TWILIO_FROM_NUMBER),
				String.format(Config.SENDGRID_EMAIL_CONTENT, numPackages)).create();

		return m.getStatus();
	}
}
