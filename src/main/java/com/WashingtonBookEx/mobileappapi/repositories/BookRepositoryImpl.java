package com.WashingtonBookEx.mobileappapi.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.WashingtonBookEx.mobileappapi.domain.AuthKey;
import com.WashingtonBookEx.mobileappapi.domain.Book;
import com.WashingtonBookEx.mobileappapi.exceptions.EtAuthException;


@Repository
public class BookRepositoryImpl implements BookRepository {

	@Autowired
	JdbcTemplate  jdbcTemplate;
	
	private static final String SQL_GET_BOOKS= "SELECT * FROM books;";
	private static final String SQL_FIND_BOOK_BY_ID = "SELECT * FROM books WHERE BOOKID = ?;";
	private static final String SQL_REQUEST_BOOK_UPDATE = "UPDATE books SET CURRENT_STATUS = \"Requested\"  WHERE BOOKID = ?;";
	private static final String SQL_REQUEST_BOOK_INSERT = "INSERT INTO requests (REQUESTERID, BOOKID, TIMESTAMP) VALUES (?,?,?);";
	private static final String SQL_DONATE_BOOK = "INSERT INTO books (BOOK_NAME, CURRENTUSERID, CURRENT_STATUS, SUBJECT_NAME, PUBLISHING_COMPANY, CURRENT_CONDITION,IMAGE_URL,ADDITIONAL_NOTES,DONATOR_ID) VALUES (?,?,?,?,?,?,?,?,?);";
	private static final String SQL_GET_MY_BOOKS = "SELECT * FROM books WHERE CURRENTUSERID = ?";
	
	public List<Book> getBooks() {
		System.out.println("1" + jdbcTemplate.getDataSource());
		List<Book> returnedBooks = jdbcTemplate.query(SQL_GET_BOOKS,bookRowMapper);
		for(Book b:returnedBooks) {
			System.out.println(b.toString());
			
		}
		return returnedBooks;
	}

	public Book findBookByID (int bookID) {
		return jdbcTemplate.queryForObject(SQL_FIND_BOOK_BY_ID, new Object[]{bookID}, bookRowMapper);
	}
	
	public Integer requestBook (int bookID, int requesterID) {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_REQUEST_BOOK_UPDATE,Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, bookID);
				System.out.println("\n"+ps.toString());
				return ps;
			}, keyHolder);
			System.out.println("Request Update Keyholder Value: " + keyHolder.getKey());
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps2 = connection.prepareStatement(SQL_REQUEST_BOOK_INSERT,Statement.RETURN_GENERATED_KEYS);
					ps2.setInt(1, requesterID);
					ps2.setInt(2,bookID);
					Date date = new Date();
					ps2.setTimestamp(3, new Timestamp(date.getTime()));
				System.out.println("\n"+ps2.toString());
				return ps2;
			}, keyHolder);
			
			return Integer.parseInt(keyHolder.getKey().toString());
		}
		catch (Exception e){
			e.printStackTrace();
			throw new EtAuthException("Invalid book request");
		}
	}
		
	//Done with new design parameters
	public Integer donateBook (Book donatedBook) {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_DONATE_BOOK,Statement.RETURN_GENERATED_KEYS);
					ps.setString(1,donatedBook.getBookName());
					ps.setInt(2,donatedBook.getCurrentUserID());
					//ps.setString(3,donatedBook.getStatus());
					ps.setString(3,"Not Available");
					ps.setString(4, donatedBook.getSubject());
					ps.setString(5, donatedBook.getPublishingCompany());
					ps.setString(6, donatedBook.getCondition());
					ps.setString(7, donatedBook.getImageUrl());
					ps.setString(8, donatedBook.getAdditionalNotes());
					ps.setInt(9, donatedBook.getDonatorID());
				System.out.println("\n"+ps.toString());
				return ps;
			}, keyHolder);
			
			return Integer.parseInt(keyHolder.getKey().toString());

		}
		catch(Exception e){
			//e.printStackTrace();
			throw new EtAuthException("Book donation failed");
		}
	}
	
	public List<Book> getMyBooks(int userID) {
		
		List<Book> returnedBooks = jdbcTemplate.query(connection ->{
			PreparedStatement ps = connection.prepareStatement(SQL_GET_MY_BOOKS);
			ps.setInt(1, userID);
			return ps;
		},bookRowMapper);
		
		for(Book b:returnedBooks) {
			System.out.println(b.toString());
			
		}
		return returnedBooks;

	}
	
	
	private RowMapper<Book> bookRowMapper = ((rs, rowNum) -> {
			return new Book(rs.getInt("BOOKID"),rs.getString("BOOK_NAME"), rs.getInt("CURRENTUSERID"), 
						rs.getString("CURRENT_STATUS"), rs.getString("SUBJECT_NAME"), rs.getString("PUBLISHING_COMPANY"), 
						rs.getString("CURRENT_CONDITION"),rs.getString("IMAGE_URL"),rs.getString("ADDITIONAL_NOTES"),rs.getInt("DONATOR_ID"));
		});

}
