package com.app.property.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ps_user_map")
public class UserMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Column(name="user_id")
	private long userId;
	
	@NotNull
	@Column(name="address_id")
	private long addressId;
	
	@NotNull
	@Column(name="project_id")
	private long projectId;
	
	@NotNull
	@Column(name="property_id")
	private long property_id;

}
