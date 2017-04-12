package com.app.property.service.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.property.service.dao.AddressDAO;
import com.app.property.service.dto.AddressDTO;
import com.app.property.service.dto.AddressSearchInputDTO;
import com.app.property.service.models.Address;
import com.app.property.service.utils.GeoLocation;

@Repository
public class AddressBO {

	public static String SOLR_ADDRESS_COLLECTION = "http://localhost:8983/solr/property-address";
	
	public AddressDTO getAddress(long addressId) throws Exception {
		Address address = addressDAO.getById(addressId);
		
		if(address != null) {
			return address.toDTO();
		}else {
			throw new RuntimeException("Invalid address id : " + addressId);
		}
	}
	
	public AddressDTO addAddress(AddressDTO dto) {
		dto = GeoLocation.getLocation(dto);
		
		Address address = addressDAO.addAddress(dto.toModel());
		dto.id = address.getId();
		indexAddress(dto);

		return address.toDTO();
	}
	
	public AddressDTO updateAddress(AddressDTO dto) {
		Address address = addressDAO.getById(dto.id);
		if(address.getId() == dto.id) {
			address = dto.updateModel(address);
			addressDAO.update(address);
		}
		return address.toDTO();
	}
	
	//public List<AddressDTO> getAddressByDistance(AddressSearchInputDTO dto)
	public SolrDocumentList getAddressByDistance(AddressSearchInputDTO dto)
			throws IOException {
		AddressDTO addressDTO = GeoLocation.getLocation(dto.getAddressDTO());
		SolrClient solr = new HttpSolrClient.Builder(SOLR_ADDRESS_COLLECTION).build();

		// Form the SolrQuery object
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.set("start", 0);
		query.set("rows", 100);
		query.set("wt", "json");
		// query.addFilterQuery("eventEndDate:" + "[NOW TO *]");

		query.addFilterQuery("{!geofilt sfield=location pt=" + addressDTO.latitude + "," + addressDTO.longitude + " d=" + dto.distance + " sort=geodist() }");
		//query.set("sort", "store asc");

		QueryResponse response = null;
		SolrDocumentList results = null;
		try {
			response = solr.query(query);
			results = response.getResults();
		} catch (org.apache.solr.client.solrj.SolrServerException e) {
			e.printStackTrace();
		} finally {
			solr.close();
		}
		/*
		List<AddressDTO> resultDTO = new ArrayList<AddressDTO>();
		if(results != null && results.size() > 0) {
			SolrDocument doc = null;
			for(int index=1; index < results.size(); index++) {
				doc = results.get(index);
				resultDTO.add(new AddressDTO(doc));
			}
		}

		return resultDTO;
		*/
		return results;
	}
	
	public void indexAddress(AddressDTO dto)  {
		SolrClient solr = new HttpSolrClient.Builder(SOLR_ADDRESS_COLLECTION).build();

		SolrInputDocument solrInput = new SolrInputDocument();
		solrInput.addField("id", dto.id);		
		solrInput.addField("street", dto.street);
		solrInput.addField("area", dto.area);
		solrInput.addField("city", dto.city);
		solrInput.addField("state", dto.state);
		solrInput.addField("country", dto.country);
		solrInput.addField("zip", dto.zipcode);
		solrInput.addField("location", dto.latitude + "," + dto.longitude);

		try {
			UpdateResponse res = solr.add(solrInput);
			System.out.println(res);
			
			res = solr.commit();
			System.out.println(res);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				solr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Autowired
	private AddressDAO addressDAO;

}
