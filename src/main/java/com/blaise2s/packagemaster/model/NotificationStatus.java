package com.blaise2s.packagemaster.model;

import com.twilio.rest.api.v2010.account.Message.Status;

public class NotificationStatus {
	private int sendGridStatusCode;
	private Status twillioStatus;

	public NotificationStatus(int sendGridStatusCode, Status twillioStatus) {
		this.sendGridStatusCode = sendGridStatusCode;
		this.twillioStatus = twillioStatus;
	}

	public int getSendGridStatusCode() {
		return sendGridStatusCode;
	}

	public void setSendGridStatusCode(int sendGridStatusCode) {
		this.sendGridStatusCode = sendGridStatusCode;
	}

	public Status getTwillioStatus() {
		return twillioStatus;
	}

	public void setTwillioStatus(Status twillioStatus) {
		this.twillioStatus = twillioStatus;
	}

}
