package com.app.property.service.dto;

import com.app.property.service.models.Property;

public class PropertyDTO {
	public long id;
	public long bedrooms;
	public double price;
	public boolean isNew;
	public long addressId;
	public long projectId;
	public long userId;
	
	public Property toModel() {
		return updateModel(new Property());
	}
	
	public Property updateModel(Property property) {
		property.setBedrooms(this.bedrooms);
		property.setPrice(this.price);
		property.setNew(this.isNew);
		
		if(this.addressId > 0 ) {
			property.setAddressId(this.addressId);
		}
		
		if(this.projectId > 0 ) {
			property.setAddressId(this.projectId);
		}
		
		if(this.id > 0 ) {
			property.setUserId(this.userId);
		}
		
		if(this.id > 0 ) {
			property.setAddressId(this.id);
		}
		
		return property;
	}
}
