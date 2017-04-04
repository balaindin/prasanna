package com.app.property.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.property.service.bo.AddressBO;
import com.app.property.service.dto.AddressDTO;
import com.app.property.service.dto.WSOutputDTO;

@Controller
@RequestMapping("/address")
public class AddressService {
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public WSOutputDTO<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
		WSOutputDTO<AddressDTO> output = new WSOutputDTO<AddressDTO>();

		try {
			output.setObject(addressBO.addAddress(addressDTO));
			output.setMessage("Address succesfully added!");
		} catch (Exception ex) {
			output.setError("Error - adding the address");
		}
		
		return output;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateAddress(@RequestBody AddressDTO addressDTO) {
		WSOutputDTO<AddressDTO> output = new WSOutputDTO<AddressDTO>();

		try {
			output.setObject(addressBO.updateAddress(addressDTO));
		} catch (Exception ex) {
			return ex.toString();
		}
		return "Address succesfully updated!";
	}

	@RequestMapping(value = "/get/{addressId}", method = RequestMethod.GET)
	@ResponseBody
	public WSOutputDTO<AddressDTO> getAddress(@PathVariable("addressId") long addressId) {
		WSOutputDTO<AddressDTO> output = new WSOutputDTO<AddressDTO>();
		
		try {
			output.setObject(addressBO.getAddress(addressId));
			output.setMessage("Address succesfully retrieved!");
		} catch (Exception ex) {
			output.setError(ex.getMessage());
		}
		return output;
	}
	
	@Autowired
	private AddressBO addressBO;
}
