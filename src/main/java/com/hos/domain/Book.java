package com.hos.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="book_id")
    private int bookId;
    
    @Column(name="name")
    private String name;
    
    @Column(name="copy_no")
    private String copyNo;
    
    @Column(name="author")
    private String author;
    
    @Column(name="print_year")
    private String printYear;
    
    @Column(name="reserve_no")
    private String reserveNo;

	public int getBookId() {
		return bookId;
	}

	public String getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(String reserveNo) {
		this.reserveNo = reserveNo;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCopyNo() {
		return copyNo;
	}

	public void setCopyNo(String copyNo) {
		this.copyNo = copyNo;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrintYear() {
		return printYear;
	}

	public Book(int bookId, String name, String copyNo, String author, String printYear, String reserveNo) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.copyNo = copyNo;
		this.author = author;
		this.printYear = printYear;
		this.reserveNo = reserveNo;
	}

	public void setPrintYear(String printYear) {
		this.printYear = printYear;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Book() {
		super();
	}

}
