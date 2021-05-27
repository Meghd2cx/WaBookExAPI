package com.WashingtonBookEx.mobileappapi.services;

import java.util.List;

import com.WashingtonBookEx.mobileappapi.models.Book;

public interface BookService {
	
	List<Book> getBooks(String authKey);
	
	Book requestBook(int bookID, int requesterID, String authKey);

	Book donateBook(Book donBook, String authKey);

	List<Book> getMyBooks(int userID, String authKey);
	
}
