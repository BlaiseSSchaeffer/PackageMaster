package com.blaise2s.packagemaster.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity(name = "Configuration")
@Table(name = "Configuration")
@JsonPropertyOrder({ "id", "name", "name" })
@Access(AccessType.PROPERTY)
@NamedQueries({ @NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c"),
		@NamedQuery(name = "Configuration.update", query = "UPDATE Configuration c SET c.value = ?1 WHERE c.name = ?2"),
		@NamedQuery(name = "Configuration.delete", query = "DELETE FROM Configuration c WHERE c.name = ?1") })
public class Configuration {
	private Integer id;
	private String name;
	private String value;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false, unique = true)
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
