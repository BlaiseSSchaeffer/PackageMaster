package com.blaise2s.packagemaster.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity(name = "Configuration")
@Table(name = "Configuration")
@JsonPropertyOrder({ "name", "value" })
@Access(AccessType.PROPERTY)
@NamedQueries({ @NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c") })
public class Configuration {
	private String name;
	private String value;

	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
