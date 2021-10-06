package com.WashingtonBookEx.mobileappapi.models;

import java.util.Date;

public class User {
	
	private Integer userID;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Date birthDate;
	private String password;
	private String streetAddress;
	private String city;
	private String county;
	private String state;
	private String schoolName;
	private boolean isTeacher;
	
	private String sessionKey;
	

	public User(String username, String firstName, String lastName, String email, Date birthDate, 
			String password, String streetAddress, String city, String county, String state, String schoolName) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.streetAddress = streetAddress;
		this.city = city;
		this.county = county;
		this.state = state;
		this.schoolName = schoolName;
		this.birthDate = birthDate;
	}
	
	public User(String username, String firstName, String lastName, String email, Date birthDate, 
			String password, String streetAddress, String city, String county, String state, 
			String schoolName, boolean isTeacher) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.streetAddress = streetAddress;
		this.city = city;
		this.county = county;
		this.state = state;
		this.schoolName = schoolName;
		this.birthDate = birthDate;
		this.isTeacher = isTeacher;
	}
	
	public User (int userID, String username, String firstName, String lastName, String email, 
			Date birthDate, String password, String address, String city, String county, String state, 
			String schoolName, boolean isTeacher) {
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.password = password;
		this.streetAddress = address;
		this.city = city;
		this.county = county;
		this.state = state;
		this.schoolName = schoolName;
		this.isTeacher = isTeacher;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setBirthdate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Date getBirthdate() {
		return this.birthDate;
	}
	
	public void setIsTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}
	
	public boolean getIsTeacher() {
		return this.isTeacher;
	}
	
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public String getSessionKey() {
		return this.sessionKey;
	}
}
