package com.app.property.service.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.property.service.dto.AddressDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GeoLocation {

	public static void main(String[] args) {
		AddressDTO dto = new AddressDTO();
		dto.street = "540 Carillon Parkway";
		dto.area = "";
		dto.city = "St Petersburg";
		dto.state = "State";
		dto.zipcode = 33716;
		GeoLocation.getLocation(dto);
	}

	private static String getAddressString(AddressDTO dto) {
		String address = null;
		address = dto.street + ",+";
		address += dto.area + ",+";
		address += dto.city + ",+";
		address += dto.state + ",+";
		address += dto.country + ",+";
		address += dto.zipcode;

		address = address.replace(' ', '+');

		return address;
	}

	private static LatLong getLocationFromGoogle(AddressDTO dto) {
		LatLong latLong = null;
		try {
			Client client = Client.create();
			String address = getAddressString(dto);

			System.out.println(address);
			
			WebResource webResource = client
					.resource("https://maps.googleapis.com/maps/api/geocode/json?address=" + address);

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			JSONObject jsnobject = new JSONObject(output);
			JSONArray jsonArray = jsnobject.getJSONArray("results");
			JSONObject location = jsonArray.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

			if (location != null) {
				System.out.println(location);
				latLong = new LatLong();
				latLong.lat = Double.parseDouble(location.get("lat").toString());
				latLong.lng = Double.parseDouble(location.get("lng").toString());
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			latLong.lat = -1;
			latLong.lng = -1;
		}
		
		return latLong;
	}
	
	public static AddressDTO getLocation(AddressDTO dto) {
		dto.street = (dto.street == null)? "" : dto.street; 
		dto.area = (dto.area == null)? "" : dto.area; 
		dto.city = (dto.city == null)? "" : dto.city; 
		dto.state = (dto.state == null)? "" : dto.state; 
		dto.country = (dto.country == null)? "" : dto.country; 			
			
		LatLong latLong = getLocationFromGoogle(dto);
		
		AddressDTO tempDTO = null;
		
		// Ignore street
		if(latLong == null) {
			tempDTO = new AddressDTO(dto);
			tempDTO.street = "";
			latLong = getLocationFromGoogle(tempDTO);
		}
		
		// Ignore area
		if(latLong == null) {
			tempDTO = new AddressDTO(tempDTO);
			tempDTO.street = "";
			latLong = getLocationFromGoogle(tempDTO);
		}
		
		dto.latitude = latLong.lat;
		dto.longitude = latLong.lng;
		
		return dto;
	}
	
}

class LatLong {
	public double lat;
	public double lng;
}
