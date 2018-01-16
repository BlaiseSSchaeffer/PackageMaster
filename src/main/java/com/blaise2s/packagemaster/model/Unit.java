package com.blaise2s.packagemaster.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity(name = "Unit")
@Table(name = "Unit")
@Access(value = AccessType.PROPERTY)
@JsonPropertyOrder({ "id", "bedrooms", "rent", "occupied", "leaseTerm", "leaseStart" })
@NamedQueries({ @NamedQuery(name = "Unit.findAll", query = "SELECT u FROM Unit u") })
public class Unit {
	private String id;
	private String bedrooms;
	private Float rent;
	private Boolean occupied;
	private Integer leaseTerm;
	private Date leaseStart;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}

	@Column(nullable = false)
	public Float getRent() {
		return rent;
	}

	public void setRent(Float rent) {
		this.rent = rent;
	}

	@Column(nullable = false)
	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	@JsonInclude(Include.NON_NULL)
	public Integer getLeaseTerm() {
		return leaseTerm;
	}

	public void setLeaseTerm(Integer leaseTerm) {
		this.leaseTerm = leaseTerm;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	public Date getLeaseStart() {
		return leaseStart;
	}

	public void setLeaseStart(Date leaseStart) {
		this.leaseStart = leaseStart;
	}

}
