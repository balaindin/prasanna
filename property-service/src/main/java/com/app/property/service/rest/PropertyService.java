package com.app.property.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.property.service.bo.PropertyBO;
import com.app.property.service.dto.PropertyDTO;
import com.app.property.service.dto.PropertyOutputDTO;
import com.app.property.service.dto.WSOutputDTO;

@Controller
@RequestMapping("/property")
public class PropertyService {

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public WSOutputDTO<PropertyDTO> addProperty(@RequestBody PropertyDTO propertyDTO) {
		WSOutputDTO<PropertyDTO> output = new WSOutputDTO<PropertyDTO>();

		try {
			output.setObject(propertyBO.addProperty(propertyDTO));
			output.setMessage("Property succesfully added!");
		} catch (Exception ex) {
			output.setError("Error - adding the Property");
		}
		
		return output;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateProperty(@RequestBody PropertyDTO propertyDTO) {
		try {
			WSOutputDTO<PropertyDTO> output = new WSOutputDTO<PropertyDTO>();
			output.setObject(propertyBO.updateProperty(propertyDTO));
		} catch (Exception ex) {
			return ex.toString();
		}
		return "Property succesfully updated!";
	}

	@RequestMapping(value = "/get/{propertyId}", method = RequestMethod.GET)
	@ResponseBody
	public WSOutputDTO<PropertyOutputDTO> getProperty(@PathVariable("propertyId") long propertyId) {
		WSOutputDTO<PropertyOutputDTO> output = new WSOutputDTO<PropertyOutputDTO>();
		
		try {
			output.setObject(propertyBO.getProperty(propertyId));
			output.setMessage("Property succesfully retrieved!");
		} catch (Exception ex) {
			output.setError(ex.getMessage());
		}
		return output;
	}
		
	@Autowired
	private PropertyBO propertyBO;
}
