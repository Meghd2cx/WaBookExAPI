package com.WashingtonBookEx.mobileappapi.services;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;

public interface AuthService {

	AuthKey addAuthKey (String username, String password,String authKey);
	
	AuthKey addAuthKey (String username, String password);
	
	Boolean authenticateKey (String authKey);
	
}
