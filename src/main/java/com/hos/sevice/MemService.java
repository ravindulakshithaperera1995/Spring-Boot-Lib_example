package com.hos.sevice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hos.domain.Member;
import com.hos.repo.MemberRepo;

@Service
public class MemService {

	@Autowired
	private MemberRepo memberRepo;
	
	String line="";
	
	public void saveMemberData() {
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/member.csv"));
			while((line=br.readLine()) != null) {
				
				String [] data = line.split(",");
				Member m = new Member();
				
				m.setfName(data[0]);
				m.setlName(data[1]);
				m.setAddress(data[3]);
				m.setNic(data[4]);
				
				memberRepo.save(m);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
