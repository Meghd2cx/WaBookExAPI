package com.WashingtonBookEx.mobileappapi.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WashingtonBookEx.mobileappapi.domain.Book;
import com.WashingtonBookEx.mobileappapi.services.BookService;



@RestController
@RequestMapping("api/books")
public class BookResource {
	
	@Autowired
	BookService bookService;
	@PostMapping("/getBooks")
	public List<Book> getBooks(){
		return bookService.getBooks();
	}
	
	@PostMapping("/getMyBooks")
	public List<Book> getMyBooks(@RequestBody Map<String,Object> responseMap){
		int userID = (int) responseMap.get("userID");
		
		return bookService.getMyBooks(userID);
	}
	
	@PostMapping("/requestBook")
	public Book requestBook(@RequestBody Map<String, Object> requestMap){
		int bookID =  (int) requestMap.get("bookID");
		int requesterID = (int) requestMap.get("requesterID");
		return bookService.requestBook(bookID,requesterID);
	}

	@PostMapping("/donateBook")
	public Book donateBook (@RequestBody Map<String,Object> bookMap) {
		String bookName = (String)bookMap.get("bookName");
		int currentUserID = (int) bookMap.get("currentUserID");
		String status = (String) bookMap.get("status");
		String subject = (String) bookMap.get("subject");
		String publishingCompany = (String) bookMap.get("publishingCompany");
		String condition = (String) bookMap.get("condition");
		String additionalInfo = "";
		int donatorID = (int) bookMap.get("donatorID");
		try{
			additionalInfo = (String) bookMap.get("additionalInfo");
		}
		catch(NullPointerException e) {
			System.out.println("NO ADDITIONAL INFO PROVIDED");
		}
		
		Book donBook = new Book(-1,bookName,currentUserID,status,subject,publishingCompany,condition,additionalInfo,donatorID);
		return bookService.donateBook(donBook);
	}
}
