package com.globallogic.dogo.mo;

import com.globallogic.dogo.domain.Project;

public class ProjectMO {
	
	public static final Long ID = 1L;
	public static final String DESCRIPTION = "thisIsADescription";
	public static final String NAME = "thisIsAName";

	public static Project build(){
		Project result = new Project();
		result.setId(ID);
		result.setDescription(DESCRIPTION);
		result.setName(NAME);
		return result;
	}
}
