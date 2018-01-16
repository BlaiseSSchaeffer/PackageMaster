package com.blaise2s.packagemaster.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity(name = "Delivery")
@Table(name = "Delivery")
@Access(value = AccessType.PROPERTY)
@JsonPropertyOrder({ "id", "residentId", "packages", "notified", "notificationStatus", "pickup" })
@NamedQueries({ @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d") })
public class Delivery {
	private Integer id;
	private Integer residentId;
	private Integer packages;
	private Date notified;
	private NotificationStatus notificationStatus;
	private Date pickup;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "RESIDENT_ID", nullable = false)
	public Integer getResidentId() {
		return residentId;
	}

	public void setResidentId(Integer residentId) {
		this.residentId = residentId;
	}

	@Column(nullable = false)
	public Integer getPackages() {
		return packages;
	}

	public void setPackages(Integer packages) {
		this.packages = packages;
	}

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getNotified() {
		return notified;
	}

	public void setNotified(Date notified) {
		this.notified = notified;
	}

	@OneToOne(optional = false)
	@JoinColumn(nullable = false)
	public NotificationStatus getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(NotificationStatus notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	public Date getPickup() {
		return pickup;
	}

	public void setPickup(Date pickup) {
		this.pickup = pickup;
	}

}
