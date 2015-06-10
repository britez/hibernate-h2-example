package com.globallogic.dogo.representation;

public class ProjectRepresentation {
	
	private Long id;
	private String name;
	private String description;
	private String statics = "%s/statics";
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatics(){
		return statics;
	}

	public void setStatics(String baseURL) {
		this.statics = String.format(this.statics, baseURL);
	}
}
