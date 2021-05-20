package com.WashingtonBookEx.mobileappapi.services;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WashingtonBookEx.mobileappapi.domain.User;
import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;
import com.WashingtonBookEx.mobileappapi.repositories.AuthRepository;
import com.WashingtonBookEx.mobileappapi.repositories.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthRepository authRepository;
	
	@Override
	public User validateUser(String email, String password, String authKey) {
		if (authRepository.authenticateKey(authKey)) {
		
			if(email != null) email = email.toLowerCase();
			return userRepository.findByEmailAndPassword(email, password);	 
		}
		
		throw new EtAuthException("Invalid authKey. Failed to authenticate user login.");
		
	}
	
	public User validateUser(String email, String password) {
		//userRepository.authenticateKey(authKey);
		
		if(email != null) email = email.toLowerCase();
	
		return userRepository.findByEmailAndPassword(email, password);	 
	}

	@Override
	public User registerUser(User inputUser, String authKey) {
		if(authRepository.authenticateKey(authKey)) {
		
			Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		
			if(inputUser.getEmail() != null) inputUser.setEmail(inputUser.getEmail().toLowerCase());
			if(!pattern.matcher(inputUser.getEmail()).matches()) throw new EtAuthException("Invalid Email Format");
		
			Integer count = userRepository.getCountByEmail(inputUser.getEmail());
		
			if (count > 0) throw new EtAuthException("Email already in use");
		
			Integer userID = userRepository.registerUser(inputUser);
			return userRepository.findById(userID);
		}
		
		else {
			throw new EtAuthException("Invalid authKey. Failed to register new user.");
		}
		
	}

}
