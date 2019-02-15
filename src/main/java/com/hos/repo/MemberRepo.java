package com.hos.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import com.hos.domain.Member;

public interface MemberRepo extends CrudRepository<Member,Integer>{

	List<Member> findByMemberId(int memberId);
	
	List<Member> findByFNameAndLName(String fName,String lName);
	
	void deleteByMemberId(int memberid);
}
