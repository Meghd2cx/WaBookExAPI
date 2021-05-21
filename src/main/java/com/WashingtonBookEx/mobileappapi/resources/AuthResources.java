package com.WashingtonBookEx.mobileappapi.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.repositories.AuthRepository;
import com.WashingtonBookEx.mobileappapi.services.AuthService;

@RestController
@RequestMapping("api/auth")
public class AuthResources {

	
	@Autowired
	AuthService authService;
	
	@PostMapping("/initAuthKey")
	public ResponseEntity<Map<String,Object>> initAuthKey (@RequestBody Map<String, Object> userMap){
		String authKey = (String) userMap.get("authKey");
		String email = (String) userMap.get("email");
		String password = (String) userMap.get("password");
		AuthKey retKey = null;
		if(authKey != null) 
			retKey = authService.addAuthKey(email, password, authKey);
		else
			retKey = authService.addAuthKey(email, password);
				
		Map<String,Object> map = new HashMap<>();
		
		map.put("UserID", retKey.getUserID());
		map.put("AuthKey", retKey.getAuthKeyValue());
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@PostMapping("/authenticateKey")
	public ResponseEntity<Map<String,String>> authenticateKey (@RequestBody Map<String,Object> userMap){
		String authKey = (String) userMap.get("authKey");
		Boolean isAuth = authService.authenticateKey(authKey);
		
		Map<String,String> map = new HashMap<>();
		
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	
	
}
