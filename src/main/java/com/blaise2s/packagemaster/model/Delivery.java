package com.blaise2s.packagemaster.model;

public class Delivery {
	private Resident recipient;
	private Integer numPackages;

	public Resident getRecipient() {
		return recipient;
	}

	public void setRecipient(Resident recipient) {
		this.recipient = recipient;
	}

	public Integer getNumPackages() {
		return numPackages;
	}

	public void setNumPackages(Integer numPackages) {
		this.numPackages = numPackages;
	}

}
