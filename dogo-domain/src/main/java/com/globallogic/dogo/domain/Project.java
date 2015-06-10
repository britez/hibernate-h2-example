package com.globallogic.dogo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class Project {
	
	private Long id;
	private String description;
	private String name;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}