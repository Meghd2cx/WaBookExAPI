package com.WashingtonBookEx.mobileappapi.repositories;

import java.sql.PreparedStatement;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;
import com.WashingtonBookEx.mobileappapi.models.AuthKey;
import com.WashingtonBookEx.mobileappapi.models.User;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

	
	private static final String SQL_AUTHENTICATE = "SELECT AUTH_KEY FROM apiAuthKeys WHERE AUTH_KEY = ?";
	private static final String SQL_ADD_AUTH_KEY_USERID = "INSERT INTO apiAuthKeys (USERID, AUTH_KEY) VALUES (?,?)";
	private static final String SQL_ADD_AUTH_KEY = "INSERT INTO apiAuthKeys (AUTH_KEY) VALUES (?)";

	@Autowired
	JdbcTemplate jdbcTemplate;
		
	@Override
	public boolean authenticateKey(String authKey) throws EtAuthException {
		try {
			
			String retAuthKey = jdbcTemplate.queryForObject(SQL_AUTHENTICATE,new Object[] {authKey}, String.class);
			return true;
		}
		catch(Exception e) {
			//e.printStackTrace();
			throw new EtAuthException("Invalid authKey. Failed to authenticate.");
		}
	}

	@Override
	public AuthKey addAuthKey(int userID, String authKey) throws EtAuthException {
		//String hashedKey = BCrypt.hashpw(authKey, BCrypt.gensalt(10));
		try {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_ADD_AUTH_KEY_USERID);
				ps.setInt(1, userID);
				ps.setString(2, authKey);
				System.out.println("\n"+ps.toString());
				return ps;
			});
			return new AuthKey(userID, authKey);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			throw new EtAuthException("Invalid details. Failed to add authKey");
		}
		
	}
		
	private RowMapper<AuthKey> authKeyRowMapper = ((rs, rowNum) -> {
		return new AuthKey(rs.getInt("AUTH_KEY"),rs.getString("USERID"));
	});
	
}


