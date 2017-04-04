package com.app.property.service.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ps_testimonial")
public class Testimonial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
/*
	@MapsId @OneToOne
	@JoinColumn(name = "id")
	private User user;
	*/	
	@NotNull
	private String testimonial;
	
	@NotNull
	private Date timestamp;

}
