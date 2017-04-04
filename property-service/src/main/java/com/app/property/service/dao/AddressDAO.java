package com.app.property.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.app.property.service.models.Address;

@Repository
@Transactional
public class AddressDAO {
	
	public Address addAddress(Address address) {
		entityManager.persist(address);
		return address;
	}

/*	public Address getAddress(long addressId) {
		return (Address) entityManager.createQuery("from Address where id = :id")
				.setParameter("id", addressId).getSingleResult();
	}*/

	public Address getById(long id) {
		return entityManager.find(Address.class, id);
	}

	public Address update(Address address) {
		return entityManager.merge(address);
	}
	
	@PersistenceContext
	private EntityManager entityManager;
}
