package com.hos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reservation")
public class Reservation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReservationPK id;
	
	@DateTimeFormat
    @Column(name="reserve_date")
	private Date reserveDate;
	
	@DateTimeFormat
	@Column(name="return_date")
	private Date returnDate;
	
	@Column(name="status")
	private String status;

	public ReservationPK getId() {
		return id;
	}

	public void setId(ReservationPK id) {
		this.id = id;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Reservation() {
		super();
	}
	
}
