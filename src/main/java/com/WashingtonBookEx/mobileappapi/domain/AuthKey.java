package com.WashingtonBookEx.mobileappapi.domain;

public class AuthKey {

	
	private String authKeyValue;
	private String platform;
	public AuthKey(String authKey, String platform) {
		super();
		this.authKeyValue = authKey;
		this.platform = platform;
	}
	public String getAuthKeyValue() {
		return authKeyValue;
	}
	public void setAuthKeyValue(String authKey) {
		this.authKeyValue = authKey;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public String toString() {
		return "{"+this.authKeyValue+","+this.platform+"}";
	}
	
}
