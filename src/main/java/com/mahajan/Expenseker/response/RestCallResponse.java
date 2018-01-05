package com.mahajan.Expenseker.response;

public class RestCallResponse {
	
	String status="Success";
	String description = " added successfully";
	
	public RestCallResponse(String resourceName) {
		description = resourceName + description;
	}
	
	public RestCallResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
