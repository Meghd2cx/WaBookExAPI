package com.WashingtonBookEx.mobileappapi.models;

public class AuthKey {

	
	private String authKeyValue;
	private int userID;
	
	public AuthKey(int userID, String authKey) {
		super();
		this.authKeyValue = authKey;
		this.userID = userID;
	}
	
	public String getAuthKeyValue() {
		return authKeyValue;
	}
	
	public void setAuthKeyValue(String authKey) {
		this.authKeyValue = authKey;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String toString() {
		return "{"+this.authKeyValue+","+this.userID+"}";
	}
	
}
