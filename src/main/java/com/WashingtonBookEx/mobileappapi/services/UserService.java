package com.WashingtonBookEx.mobileappapi.services;

import com.WashingtonBookEx.mobileappapi.domain.User;
import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;


public interface UserService {
	
	User registerUser(User inputUser, String authKey);

	User validateUser(String email, String password, String authKey);

	User validateUser(String email, String password);

}
