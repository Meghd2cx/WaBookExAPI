package com.WashingtonBookEx.mobileappapi.repositories;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.domain.User;
import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;

public interface UserRepository {

	
	//Integer create(String username, String firstName, String lastName, String email, int age, String password, String streetAddress, String city, String county, String state, String schoolName) throws EtAuthException;
	
	Integer registerUser(User inputUser) throws EtAuthException;
	
	User findByEmailAndPassword(String email, String password) throws EtAuthException;
	
	Integer getCountByEmail(String email);
	
	User findById(Integer userID);
	
}
