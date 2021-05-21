package com.WashingtonBookEx.mobileappapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.domain.User;
import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;
import com.WashingtonBookEx.mobileappapi.repositories.AuthRepository;
import com.WashingtonBookEx.mobileappapi.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	UserService userService;
	
	 
	@Override
	public AuthKey addAuthKey(String email, String password, String authKey) throws EtAuthException{
		try {
			User owner = userService.validateUser(email, password);
			return authRepository.addAuthKey(owner.getUserID(), authKey); 
		}
		catch (Exception e) {
			//e.printStackTrace();
			throw new EtAuthException("Failed to add Authentication Key");
		}
	}
	
	public AuthKey addAuthKey(String email, String password) throws EtAuthException{
		try {
			User owner = userService.validateUser(email, password);
			
			return authRepository.addAuthKey(owner.getUserID(), generateKey(30)); 
		}
		catch (Exception e) {
			//e.printStackTrace();
			throw new EtAuthException("Failed to add Authentication Key");
		}
	}

	@Override
	public Boolean authenticateKey(String authKey)
			throws EtAuthException{
		
		System.out.println(authKey);

		try{
			return authRepository.authenticateKey(authKey);
		}
		catch(Exception exception) {
			throw new EtAuthException ("Platform not verfied");
		}
		
	}
	
	public String generateKey(int length) {
		
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        
        StringBuilder sb = new StringBuilder(length);
  
        for (int i = 0; i < length; i++) {
        	int index = (int)(AlphaNumericString.length() * Math.random());
  
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
	}

}
