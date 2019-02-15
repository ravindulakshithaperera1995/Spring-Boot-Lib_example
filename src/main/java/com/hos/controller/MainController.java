package com.hos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hos.domain.Book;
import com.hos.domain.Member;

import com.hos.domain.User;
import com.hos.repo.BookRepo;
import com.hos.repo.MemberRepo;


@Controller
public class MainController {

	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@GetMapping("/home")
	public String root(Model model) {
		
		List<Member> arr = (List<Member>) memberRepo.findAll();
	    int count = arr.size();
	    model.addAttribute("count", count);
	    
	    List<Book> arr1 = (List<Book>) bookRepo.findAll();
	    int count1 = arr1.size();
	    model.addAttribute("cnt", count1);
	    
		return "Home";
	}
	
	@GetMapping("/login")
	public String login(Model model){
		model.addAttribute("usr", new User());
		return "Login";
	}
	
	@PostMapping("/log")
	public String log(@ModelAttribute("usr") User usr) {
		
		String uname = usr.getUsername();
		String pwd = usr.getPwd();
		
		if(uname == "ravindu" && pwd == "123") {
			return "Home";
		}
		else {
			return "redirect:/Login";
		}
	}

}
