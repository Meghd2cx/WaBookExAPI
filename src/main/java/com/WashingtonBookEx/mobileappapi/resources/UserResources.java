 package com.WashingtonBookEx.mobileappapi.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WashingtonBookEx.mobileappapi.domain.User;
import com.WashingtonBookEx.mobileappapi.services.AuthService;
import com.WashingtonBookEx.mobileappapi.services.UserService;

import com.WashingtonBookEx.mobileappapi.repositories.AuthRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/users")
public class UserResources {

	
	@Autowired
	UserService userService;
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> loginUser (@RequestParam(required = true) String authKey,
			@RequestBody Map<String, Object> userMap){
		String email = (String) userMap.get("email");
		String password = (String) userMap.get("password");
		System.out.println("Query: " + userMap.toString());
		User user = userService.validateUser(email, password, authKey);
		
		Map<String,Object> map = new HashMap<>();
	
		map.put("message", "loggedIn successfully");
		
		map.put("userID", user.getUserID());
		map.put("firstName", user.getFirstName());
		map.put("lastName", user.getLastName());
		map.put("username", user.getUsername());
		map.put("email", user.getEmail());
		map.put("dob", user.getBirthdate());
		map.put("streetAddress", user.getStreetAddress());
		map.put("county", user.getCounty());
		map.put("state", user.getState());
		map.put("schoolName", user.getSchoolName());
		map.put("isTeacher", user.getIsTeacher());
		
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public User registerUser(@RequestParam(required = true) String authKey, @RequestBody Map<String,Object> userMap) 
			throws ParseException {
		String username = (String) userMap.get("username");
		String firstName = (String) userMap.get("firstName");
		String lastName = (String) userMap.get("lastName");
		String email = (String) userMap.get("email");
		Date birthDate = new SimpleDateFormat("MM/dd/yyyy").parse((String) userMap.get("birthdate"));  
		String password = (String) userMap.get("password");
		String streetAddress = (String) userMap.get("streetAddress");
		String city = (String) userMap.get("city");
		String county = (String) userMap.get("county");
		String state = (String) userMap.get("state");
		String schoolName = (String) userMap.get("schoolName");
		boolean isTeacher = (boolean) userMap.get("isTeacher");
				
		User inputUser = new User(username,firstName,lastName,email,birthDate,password,streetAddress,city,county,state,schoolName,isTeacher);
		User user = userService.registerUser(inputUser, authKey);
		//Map<String,String> map = new HashMap<>();
		//map.put("message","registered successfully");
		
		return user;
		//return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
}
