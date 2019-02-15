package com.hos.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class ReservationPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="member_id")
	private int memberId;
	
	@Column(name="book_id")
	private int bookId;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReservationPK() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + memberId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationPK other = (ReservationPK) obj;
		if (bookId != other.bookId)
			return false;
		if (memberId != other.memberId)
			return false;
		return true;
	}
	
}
