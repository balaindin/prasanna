package com.app.property.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.app.property.service.dto.ProjectDTO;

@Entity
@Table(name = "ps_project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private boolean isNew;
	
	@NotNull
	private String title;

	@NotNull
	@Column(name="address_id")
	private long addressId;
	
	@NotNull
	@Column(name="user_id")
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public ProjectDTO toDTO() {
		ProjectDTO dto = new ProjectDTO();
		dto.id = this.id;
		dto.title = this.title;
		dto.isNew = this.isNew;
		dto.addressId = this.addressId;
		dto.userId = this.userId;
		
		return dto;
	}

}
