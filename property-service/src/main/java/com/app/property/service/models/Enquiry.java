package com.app.property.service.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ps_enquiry")
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@MapsId @OneToOne
	@JoinColumn(name = "id")
	private Address address;
	
	@MapsId @OneToOne
	@JoinColumn(name = "id")
	private Project project;
	
	@MapsId @OneToOne
	@JoinColumn(name = "id")
	private Property property;
	
	@NotNull
	private String requirements;


}
