package com.app.property.service.dto;

public class AddressSearchInputDTO
 {
	public long id;
	public String street;
	public String area;
	public String city;
	public String state;
	public String country;
	public long zipcode;
	public int distance;
	
	public AddressDTO getAddressDTO() {
		AddressDTO dto = new AddressDTO();
		dto.id = this.id;
		dto.street = this.street;
		dto.area = this.area;
		dto.city = this.city;
		dto.state = this.state;
		dto.country = this.country;
		dto.zipcode = this.zipcode;
		
		return dto;
	}
	
}
