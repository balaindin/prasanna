package com.app.property.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.app.property.service.dto.PropertyDTO;
import com.app.property.service.dto.PropertyOutputDTO;

@Entity
@Table(name = "ps_property")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private long bedrooms;
	
	@NotNull
	private double price;
	
	@NotNull
	@Column(name="is_new")
	private boolean isNew;
	
	@NotNull
	@Column(name="address_id")
	private long addressId;
	
	@NotNull
	@Column(name="project_id")
	private long projectId;
	
	@NotNull
	@Column(name="user_id")
	private long userId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(long bedrooms) {
		this.bedrooms = bedrooms;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long project) {
		this.projectId = project;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public PropertyDTO toDTO() {
		PropertyDTO dto = new PropertyDTO();
		dto.id = this.id;
		dto.bedrooms = this.bedrooms;
		dto.price = this.price;
		dto.isNew = this.isNew;
		dto.addressId = this.addressId;
		dto.projectId = this.projectId;
		dto.userId = this.userId;
		
		return dto;
	}
	
	public PropertyOutputDTO toOutputDTO() {
		PropertyOutputDTO dto = new PropertyOutputDTO();
		dto.property = this.toDTO();
		return dto;
	}
}
