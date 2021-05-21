package com.WashingtonBookEx.mobileappapi.repositories;
import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.exceptions.*;

public interface AuthRepository {

	boolean authenticateKey(String authKey) throws EtAuthException;
	
	AuthKey addAuthKey(int userID, String authKey) throws EtAuthException;
}
