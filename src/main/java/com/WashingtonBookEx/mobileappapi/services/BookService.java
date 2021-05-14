package com.WashingtonBookEx.mobileappapi.services;

import java.util.List;

import com.WashingtonBookEx.mobileappapi.domain.Book;

public interface BookService {

	
	List<Book> getBooks();
	
	Book requestBook(int bookID, int requesterID);

	Book donateBook(Book donBook);

	List<Book> getMyBooks(int userID);
	
}
