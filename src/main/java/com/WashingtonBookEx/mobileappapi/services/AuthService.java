package com.WashingtonBookEx.mobileappapi.services;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;

public interface AuthService {

	AuthKey addAuthKey (String authKey, String platform);
	
	AuthKey validateAuthKey (String authKey, String platform);
	
}
