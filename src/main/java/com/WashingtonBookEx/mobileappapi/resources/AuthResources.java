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
	public ResponseEntity<Map<String,String>> initAuthKey (@RequestBody Map<String, Object> userMap){
		String authKey = (String) userMap.get("authKey");
		String platform = (String) userMap.get("platform");
		
		AuthKey ret = authService.addAuthKey(authKey, platform);
				
		Map<String,String> map = new HashMap<>();
		
		map.put("message", "Key Added");
		map.put("authKey", ret.getAuthKeyValue());
		map.put("platform", ret.getPlatform());
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@PostMapping("/authenticateKey")
	public ResponseEntity<Map<String,String>> authenticateKey (@RequestBody Map<String,Object> userMap){
		String authKey = (String) userMap.get("authKey");
		String platform = (String) userMap.get("platform");
		
		AuthKey ret = authService.authenticateKey(authKey, platform);
		
		Map<String,String> map = new HashMap<>();
		
		map.put("message","Key Authenticated");
		map.put("authKey", ret.getAuthKeyValue());
		map.put("platform", ret.getPlatform());
		
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	
	
}
