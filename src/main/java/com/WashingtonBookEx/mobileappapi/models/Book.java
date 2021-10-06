package com.WashingtonBookEx.mobileappapi.models;

public class Book {

	
	private int bookID;
	private String bookName;
	private int currentUserID;
	private String status;
	private String subject;
	private String publishingCompany;
	private String condition;
	private String imageUrl = "";
	private String additionalNotes = "";
	private int donatorID = 0;
	
	public Book(int bookID, String bookName, int currentUserID, String status, String subject, String publishingCompany,
			String condition,int donatorID) {
		super();
		this.bookID = bookID;
		this.bookName = bookName;
		this.currentUserID = currentUserID;
		this.status = status;
		this.subject = subject;
		this.publishingCompany = publishingCompany;
		this.condition = condition;
		this.donatorID = donatorID;
	}
	
	public Book(int bookID, String bookName, int currentuserID, String status, String subject, String publishingCompany, String condition,
			String imageUrl, String additionalNotes,int donatorID) {
		super();
		this.bookID = bookID;
		this.bookName = bookName;
		this.currentUserID = currentuserID;
		this.status = status;
		this.publishingCompany = publishingCompany;
		this.condition = condition;
		this.imageUrl = imageUrl;
		this.additionalNotes = additionalNotes;
		this.donatorID = donatorID;
	}

	public Book(int bookID, String bookName, int currentuserID, String status, String subject, String publishingCompany, String condition,
			String additionalNotes, int donatorID) {
		super();
		this.bookID = bookID;
		this.bookName = bookName;
		this.currentUserID = currentuserID;
		this.status = status;
		this.publishingCompany = publishingCompany;
		this.condition = condition;
		this.additionalNotes = additionalNotes;
		this.donatorID = donatorID;
	}
	
	

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getCurrentUserID() {
		return currentUserID;
	}

	public void setCurrentUserID(int currentUserID) {
		this.currentUserID = currentUserID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPublishingCompany() {
		return publishingCompany;
	}

	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	
	public String toString() {
		String ret =  "{"+this.bookID+","+bookName+","+currentUserID+","+status+","+subject+","+publishingCompany+","+condition+"}";
		
		return ret;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	
	public int getDonatorID() {
		return this.donatorID;
	}
	
	public void setDonatorID(int donatorID) {
		this.donatorID = donatorID;
	}
	
}
