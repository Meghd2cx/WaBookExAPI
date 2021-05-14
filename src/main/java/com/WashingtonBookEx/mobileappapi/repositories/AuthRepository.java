package com.WashingtonBookEx.mobileappapi.repositories;
import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.exceptions.*;

public interface AuthRepository {

	
	AuthKey authenticate(String authKey, String platform) throws EtAuthException;
	
	AuthKey addAuthKey(String authKey, String platform) throws EtAuthException;
}
