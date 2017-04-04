package com.app.property.service.dto;

import java.util.ArrayList;
import java.util.List;

public class WSOutputDTO<T> {
	public T object;
	public List<String> errors;
	public List<String> messages;
	
	public void setObject(T object) {
		this.object = object;
	}

	public void setError(String error) {
		if(errors == null) {
			this.errors = new ArrayList<String>();
		}
		
		if(error != null && error.length() > 0) {
			this.errors.add(error);
		}
	}
	
	public void setMessage(String message) {
		if(messages == null) {
			this.messages = new ArrayList<String>();
		}
		
		if(message != null && message.length() > 0) {
			messages.add(message);
		}
	}
}
