package com.app.property.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ps_image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String image;
	
	@NotNull
	private String status; // Active or Inactive or Blocked

	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@NotNull
	@Column(name="is_active")
	private boolean isActive;
	
	@NotNull
	@Column(name="is_blocked")
	private boolean isBlocked;
}
