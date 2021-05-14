package com.WashingtonBookEx.mobileappapi.repositories;

import java.sql.PreparedStatement;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.domain.User;
import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

	
	private static final String SQL_AUTHENTICATE = "SELECT * FROM apiAuthKeys WHERE AUTH_KEY = ?";
	private static final String SQL_ADD_AUTH_KEY = "INSERT INTO apiAuthKeys (AUTH_KEY,PLATFORM) VALUES (?,?)";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public AuthKey authenticateKey(String authKey, String platform) throws EtAuthException {
		try {
			AuthKey retAuthKey = jdbcTemplate.queryForObject(SQL_AUTHENTICATE,new Object[] {authKey}, authKeyRowMapper);
			
			return retAuthKey;
		}
		catch(Exception e) {
			//e.printStackTrace();
			throw new EtAuthException("Invalid authKey. Failed to authenticate.");
		}
	}
	
	@Override
	public AuthKey authenticateKey(String authKey) throws EtAuthException {
		try {
			AuthKey retAuthKey = jdbcTemplate.queryForObject(SQL_AUTHENTICATE,new Object[] {authKey}, authKeyRowMapper);
			
			return retAuthKey;
		}
		catch(Exception e) {
			//e.printStackTrace();
			throw new EtAuthException("Invalid authKey. Failed to authenticate.");
		}
	}

	@Override
	public AuthKey addAuthKey(String authKey, String platform) throws EtAuthException {
		//String hashedKey = BCrypt.hashpw(authKey, BCrypt.gensalt(10));
		try {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_ADD_AUTH_KEY);
				ps.setString(1, authKey);
				ps.setString(2, platform);
				System.out.println("\n"+ps.toString());
				return ps;
			});
			
			return new AuthKey(authKey,platform);
		}
		
		catch(Exception e) {
			//e.printStackTrace();
			throw new EtAuthException("Invalid details. Failed to add authKey");
		}
		
	}
	
	public boolean authenticate(String authKey) {
		try {
				AuthKey retAuthKey = jdbcTemplate.queryForObject(SQL_AUTHENTICATE,new Object[] {authKey}, authKeyRowMapper);			
				
				return true;
			}
			catch(Exception e) {
				//e.printStackTrace();
				throw new EtAuthException("Invalid authKey. Failed to authenticate.");
			}
	}
	
	private RowMapper<AuthKey> authKeyRowMapper = ((rs, rowNum) -> {
		return new AuthKey(rs.getString("AUTH_KEY"), rs.getString("PLATFORM"));
	});
	
}


