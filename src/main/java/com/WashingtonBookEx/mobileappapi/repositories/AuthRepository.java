package com.WashingtonBookEx.mobileappapi.repositories;
import com.WashingtonBookEx.mobileappapi.exceptions.*;
import com.WashingtonBookEx.mobileappapi.models.AuthKey;

public interface AuthRepository {

	boolean authenticateKey(String authKey) throws EtAuthException;
	
	AuthKey addAuthKey(int userID, String authKey) throws EtAuthException;
}
