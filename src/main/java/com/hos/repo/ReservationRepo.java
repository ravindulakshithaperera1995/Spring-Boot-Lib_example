package com.hos.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hos.domain.Reservation;
import com.hos.domain.ReservationPK;

public interface ReservationRepo extends CrudRepository<Reservation,ReservationPK>{

	List<Reservation> findByIdBookId(int bookId);
	List<Reservation> findByIdMemberIdAndIdBookId(int memberId,int bookId);
	List<Reservation> findByIdMemberIdAndStatus(int memberId,String status);
	
	@Query(value="select * from reservation where status = 'R' ",nativeQuery = true)
	List<Reservation> findBystatus();
	
	@Query(value="select * from reservation where status = 'L' ",nativeQuery = true)
	List<Reservation> findBystatus1();
}
