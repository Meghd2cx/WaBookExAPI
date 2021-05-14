package com.WashingtonBookEx.mobileappapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WashingtonBookEx.mobileappapi.domain.Book;
import com.WashingtonBookEx.mobileappapi.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getBooks(String authKey) {
			List<Book> books = bookRepository.getBooks();
		return books;
	}
	
	public Book requestBook (int bookID, int requesterID) {
		return bookRepository.findBookByID(bookRepository.requestBook(bookID, requesterID));
	}
	
	public Book donateBook(Book donBook) {
		
		if(donBook.getPublishingCompany().toLowerCase().contains("princeton")) {
			donBook.setImageUrl("https://upload.wikimedia.org/wikipedia/en/7/72/Tpr_logo.png");
		}
		else if(donBook.getPublishingCompany().toLowerCase().contains("barron")) {
			donBook.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR02xv4hTqKhAJzkkgx7dej95noR0xZb_p7zw&usqp=CAU");
		}
		else if(donBook.getPublishingCompany().toLowerCase().contains("mcgraw")) {
			donBook.setImageUrl("https://tse4.mm.bing.net/th?id=OIP.9jcM4vANqprpGn7fogRbqgHaHa&pid=Api");
		}
		else {
			donBook.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlDKQEAGbyGndsEq_aX7H5KDPYpVGkwaUqmg&usqp=CAU");
		}
		
			
		return bookRepository.findBookByID(bookRepository.donateBook(donBook));
	}

	@Override
	public List<Book> getMyBooks(int userID) {
			
		return bookRepository.getMyBooks(userID);
	}

}
