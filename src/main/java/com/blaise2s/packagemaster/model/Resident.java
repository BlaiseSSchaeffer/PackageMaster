package com.blaise2s.packagemaster.model;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity(name = "Resident")
@Table(name = "Resident")
@Access(value = AccessType.PROPERTY)
@NamedQueries({ @NamedQuery(name = "Resident.findAll", query = "SELECT r FROM Resident r"),
		@NamedQuery(name = "Resident.findByUnit", query = "SELECT r FROM Resident r WHERE r.unit.id = ?1"), })
@JsonPropertyOrder({ "id", "firstName", "lastName", "email", "phone", "unit" })
public class Resident {
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Unit unit;
	private List<Delivery> deliveries;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false, length = 100)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(nullable = false, length = 100)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(nullable = false, length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false, length = 14)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, name = "UNIT_ID")
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Delivery.class)
	@CascadeOnDelete
	@JoinColumn(name = "RESIDENT_ID")
	@JsonInclude(Include.NON_EMPTY)
	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

}
