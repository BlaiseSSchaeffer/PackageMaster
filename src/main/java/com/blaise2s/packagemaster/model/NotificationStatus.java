package com.blaise2s.packagemaster.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity(name = "NotificationStatus")
@Table(name = "NotificationStatus")
@Access(value = AccessType.PROPERTY)
@JsonPropertyOrder({ "id", "sendGridStatusCode", "twillioStatus" })
@NamedQueries({})
public class NotificationStatus {
	private Integer id;
	private int sendGridStatusCode;
	private String twillioStatus;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public int getSendGridStatusCode() {
		return sendGridStatusCode;
	}

	public void setSendGridStatusCode(int sendGridStatusCode) {
		this.sendGridStatusCode = sendGridStatusCode;
	}

	@Column(nullable = false)
	public String getTwillioStatus() {
		return twillioStatus;
	}

	public void setTwillioStatus(String twillioStatus) {
		this.twillioStatus = twillioStatus;
	}

}
