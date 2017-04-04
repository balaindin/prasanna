package com.app.property.service.dto;

import com.app.property.service.models.Project;

public class ProjectDTO {
	public long id;
	public boolean isNew;
	public String title;
	public long addressId;
	public long userId;
	
	public Project toModel() {
		return updateModel(new Project());
	}
	
	public Project updateModel(Project project) {
		project.setNew(this.isNew);
		project.setTitle(this.title);
		project.setAddressId(this.addressId);

		if(this.id > 0 ) {
			project.setId(this.id);
		}
		
		if(this.addressId > 0 ) {
			project.setAddressId(this.addressId);
		}
		
		if(this.userId > 0 ) {
			project.setAddressId(this.userId);
		}
		
		return project;
	}
}
