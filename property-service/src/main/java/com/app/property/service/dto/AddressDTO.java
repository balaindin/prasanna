package com.app.property.service.dto;

import org.apache.solr.common.SolrDocument;

import com.app.property.service.models.Address;

public class AddressDTO
 {
	public long id;
	public String street;
	public String area;
	public String city;
	public String state;
	public String country;
	public long zipcode;
	public double latitude;
	public double longitude;
	
	public AddressDTO() {
		
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param dto
	 */
	public  AddressDTO(AddressDTO dto) {
		this.street = dto.street;
		this.area = dto.area;
		this.city = dto.city;
		this.state = dto.state;
		this.country = dto.country;
		this.zipcode = dto.zipcode;
		this.latitude = dto.latitude;
		this.longitude = dto.longitude;
	}
	
	public  AddressDTO(SolrDocument doc) {
		this.id = Integer.parseInt(doc.getFieldValue("id").toString());
		this.street = (doc.getFieldValue("street") == null) ? "" : doc.getFieldValue("street").toString();
		this.area = (doc.getFieldValue("area") == null) ? "" : doc.getFieldValue("area").toString();
		this.city = (doc.getFieldValue("city") == null) ? "" : doc.getFieldValue("city").toString();
		this.state = (doc.getFieldValue("state") == null) ? "" : doc.getFieldValue("state").toString();
		this.country = (doc.getFieldValue("country") == null) ? "" : doc.getFieldValue("country").toString();
		this.zipcode = (doc.getFieldValue("country") == null) ? 0 : Integer.parseInt(doc.getFieldValue("zip").toString());
	}
	
	public Address toModel() {
		return this.updateModel(new Address());
	}
	
	public Address updateModel(Address address) {
		address.setStreet(this.street);
		address.setArea(this.area);
		address.setCity(this.city);
		address.setState(this.state);
		address.setCountry(this.country);
		address.setZipcode(this.zipcode);
		address.setLatitude(this.latitude);
		address.setLongitude(this.longitude);
		return address;
	}

}
