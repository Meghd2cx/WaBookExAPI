package com.WashingtonBookEx.mobileappapi.repositories;
import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.exceptions.*;

public interface AuthRepository {

	
	AuthKey authenticateKey(String authKey, String platform) throws EtAuthException;
	
	AuthKey authenticateKey(String authKey) throws EtAuthException;
	
	AuthKey addAuthKey(String authKey, String platform) throws EtAuthException;
}
