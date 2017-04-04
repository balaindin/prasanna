package com.app.property.service.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.property.service.dao.AddressDAO;
import com.app.property.service.dao.PropertyDAO;
import com.app.property.service.dto.ProjectDTO;
import com.app.property.service.dto.PropertyDTO;
import com.app.property.service.dto.PropertyOutputDTO;
import com.app.property.service.models.Address;
import com.app.property.service.models.Property;

@Repository
public class PropertyBO {

	public PropertyOutputDTO getProperty(long propertyId) throws Exception {
		Property property = propertyDAO.getById(propertyId);
		
		if(property != null) {
			Address address = addressDAO.getById(property.getAddressId());
			PropertyOutputDTO dto = property.toOutputDTO();
			if(address != null) {
				dto.address = address.toDTO();
			}
			return dto;
		}else {
			throw new RuntimeException("Invalid property id : " + propertyId);
		}
	}
	
	public PropertyDTO addProperty(PropertyDTO dto) {
		if(dto.userId <= 0) {
			throw new RuntimeException("Missing user id");
		}
		
		if(dto.addressId <= 0) {
			throw new RuntimeException("Missing address id");
		}
		
		return propertyDAO.addProperty(dto.toModel()).toDTO();
	}
	
	public PropertyDTO updateProperty(PropertyDTO dto) {
		Property property = propertyDAO.getById(dto.id);
		if(property.getId() == dto.id) {
			dto.updateModel(property);
			propertyDAO.update(property);
		}
		return property.toDTO();
	}
	
	
	public ProjectDTO addProject(ProjectDTO dto) {
		if(dto.userId <= 0) {
			throw new RuntimeException("Missing user id");
		}
		
		if(dto.addressId <= 0) {
			throw new RuntimeException("Missing address id");
		}
		
		return propertyDAO.addProject(dto.toModel()).toDTO();
	}

	@Autowired
	private PropertyDAO propertyDAO;
	
	@Autowired
	private AddressDAO addressDAO;

}
