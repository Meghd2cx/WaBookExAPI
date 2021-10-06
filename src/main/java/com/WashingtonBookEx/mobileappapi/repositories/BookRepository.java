package com.WashingtonBookEx.mobileappapi.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.WashingtonBookEx.mobileappapi.models.Book;

@Repository
public interface BookRepository {

	List<Book> getBooks();
	
	Book findBookByID(int bookID);
	
	Integer requestBook(int bookID, int requesterID);
	
	Integer donateBook(Book donBook);

	List<Book> getMyBooks(int userID);
}
