package com.WashingtonBookEx.mobileappapi.repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.domain.User;
import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;


@Repository
public class UserRepositoryImpl implements UserRepository{

	private static final String SQL_CREATE = "INSERT INTO users(USER_NAME, FIRST_NAME, LAST_NAME, EMAIL, BIRTHDATE, PASSWORD, STREETADDRESS, CITY, COUNTY, STATE, SCHOOLNAME)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	
	private static final String SQL_COUNT_BY_EMAIL = "Select COUNT(*) FROM users WHERE EMAIL = ?;";
	
	private static final String SQL_FIND_BY_ID = "SELECT * FROM users WHERE USER_ID = ?;";
	
	private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM users WHERE EMAIL = ?;";
	
	private static final String SQL_AUTHENTICATE = "SELECT * FROM apiAuthKeys WHERE AUTH_KEY = ?";
	
	@Autowired
	JdbcTemplate  jdbcTemplate;
	
	public Integer registerUser(User inputUser)
			throws EtAuthException {
		String hashedPassword = BCrypt.hashpw(inputUser.getPassword(), BCrypt.gensalt(10));
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, inputUser.getUsername());
				ps.setString(2, inputUser.getFirstName());
				ps.setString(3, inputUser.getLastName());
				ps.setString(4, inputUser.getEmail());
				@SuppressWarnings("deprecation")
				Date sqlBD = new Date(inputUser.getBirthdate().getYear(),inputUser.getBirthdate().getMonth(),inputUser.getBirthdate().getDay());
				ps.setDate(5, sqlBD);
				ps.setString(6, hashedPassword);
				ps.setString(7,inputUser.getStreetAddress());
				ps.setString(8, inputUser.getCity());
				ps.setString(9, inputUser.getCounty());
				ps.setString(10, inputUser.getState());
				ps.setString(11, inputUser.getSchoolName());
				
				System.out.println("\n"+ps.toString());
				return ps;
			}, keyHolder);
				
			//return (Integer) keyHolder.getKeys().get("USER_ID");
			return Integer.parseInt(keyHolder.getKey().toString());
		}
		catch(Exception e) {
			//e.printStackTrace();
			throw new EtAuthException("Invalid details. Failed to create account.");
		}
	}

	@Override
	public User findByEmailAndPassword(String email, String password) throws EtAuthException {
		try {
			User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[] {email}, userRowMapper);
			if(!BCrypt.checkpw(password, user.getPassword()))
				throw new EtAuthException("Invalid email/password");
			System.out.println("User:" + email + " signed in");
			return user;
		}
		catch(Exception e) {
			throw new EtAuthException("Invalid email/password");
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer getCountByEmail(String email) {
		return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[] {email}, Integer.class);
	}

	@Override
	public User findById(Integer userID) {
		System.out.println(userID);
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userID}, userRowMapper);
	}

	public AuthKey authenticateKey(String authKey, String platform) {
		
		String[] verifiedPlatforms = {"android","ios","web"};
		boolean isVerifiedPlatform = false;
		for(int i = 0; i < verifiedPlatforms.length; i++) {
			if(!isVerifiedPlatform) {
				if(platform.equals(verifiedPlatforms[i]))
					isVerifiedPlatform = true;
			}
		}
		
		if(isVerifiedPlatform) {
			try {
				AuthKey retAuthKey = jdbcTemplate.queryForObject(SQL_AUTHENTICATE,new Object[] {authKey}, authKeyRowMapper);			
				
				return retAuthKey;
			}
			catch(Exception e) {
				//e.printStackTrace();
				throw new EtAuthException("Invalid authKey. Failed to authenticate.");
			}
		}
		else {
			throw new EtAuthException("API Auth Key platform not verified");
		}
		
	}
	
	public boolean authenticateKey(String authKey) {
		try {
				AuthKey retAuthKey = jdbcTemplate.queryForObject(SQL_AUTHENTICATE,new Object[] {authKey}, authKeyRowMapper);			
				
				return true;
			}
			catch(Exception e) {
				//e.printStackTrace();
				throw new EtAuthException("Invalid authKey. Failed to authenticate.");
			}
	}

	private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
		return new User(rs.getInt("USER_ID"),rs.getString("USER_NAME"),rs.getString("FIRST_NAME"),
				rs.getString("LAST_NAME"), rs.getString("EMAIL"), rs.getDate("BIRTHDATE"), rs.getString("PASSWORD"), rs.getString("STREETADDRESS"), 
				rs.getString("CITY"), rs.getString("COUNTY"), rs.getString("STATE"), rs.getString("SCHOOLNAME"));
	});
	
	private RowMapper<AuthKey> authKeyRowMapper = ((rs, rowNum) -> {
		return new AuthKey(rs.getString("AUTH_KEY"), rs.getString("PLATFORM"));
	});
}
