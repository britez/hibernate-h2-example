package com.globallogic.dogo.representation;

public class ProjectRepresentationMO {
	
	private static final String DESCRIPTION = "thisIsADescription";
	private static final String NAME = "thisIsAName";
	public static Long ID = 1L;
	
	public static ProjectRepresentation build(){
		ProjectRepresentation result = new ProjectRepresentation();
		result.setId(ID);
		result.setDescription(DESCRIPTION);
		result.setName(NAME);
		return result;
	}

}
