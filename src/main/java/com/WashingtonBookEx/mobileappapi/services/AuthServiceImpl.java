package com.WashingtonBookEx.mobileappapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;
import com.WashingtonBookEx.mobileappapi.repositories.AuthRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthRepository authRepository;
	
	 
	@Override
	public AuthKey addAuthKey(String authKey, String platform) {
		AuthKey ret = authRepository.addAuthKey(authKey, platform);
		return ret;
	}

	@Override
	public AuthKey authenticateKey(String authKey, String platform)
			throws EtAuthException{
		
		System.out.println(authKey+""+platform);

		String[] verifiedPlatforms = {"android","ios","web"};
		boolean isVerifiedPlatform = false;
		for(int i = 0; i < verifiedPlatforms.length; i++) {
			if(!isVerifiedPlatform) {
				if(platform.equals(verifiedPlatforms[i]))
					isVerifiedPlatform = true;
			}
		}
		
		if(isVerifiedPlatform) {
			AuthKey ret = authRepository.authenticateKey(authKey, platform);
			
			return ret;
		}
		else {
			throw new EtAuthException ("Platform not verfied");
		}
		
	}

}
