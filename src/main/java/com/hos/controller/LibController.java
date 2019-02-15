package com.hos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hos.domain.Book;
import com.hos.domain.Check;
import com.hos.domain.Check1;
import com.hos.domain.Librarian;
import com.hos.domain.Member;
import com.hos.domain.Reservation;
import com.hos.domain.ReservationPK;
import com.hos.domain.Search;
import com.hos.domain.Search1;
import com.hos.repo.BookRepo;
import com.hos.repo.LibRepo;
import com.hos.repo.MemberRepo;
import com.hos.repo.ReservationRepo;

@Controller
public class LibController {
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private ReservationRepo reservationRepo;
	
	@Autowired
	private LibRepo libRepo;
	//memlist
	@GetMapping("/adminhome")
	public String adHome(Model model) {
		
		List<Member> list3 = (List<Member>) memberRepo.findAll();
		int count3 = list3.size();
		model.addAttribute("count1", count3);
		
		List<Book> list2 = (List<Book>) bookRepo.findAll();
		int count4 = list2.size();
		model.addAttribute("count2", count4);
		
		List<Reservation> list = (List<Reservation>) reservationRepo.findAll();	
		int count = list.size();
		model.addAttribute("count", count);
		
		List<Reservation> list1 = reservationRepo.findBystatus();
		int count1 = list1.size();
		model.addAttribute("count3", count1);
		
		int count2 = count - count1;
		model.addAttribute("count4", count2);
		
		return "/lib/Adminhome";
	}
	
	//mem list
	@GetMapping("/memberlist")
	public String viewMemberlist(Model model) {
		
		Iterable<Member> member = memberRepo.findAll();
		model.addAttribute("members",member );
		System.out.println(member);
		return "member/MemberList";
	}
	
	//book list
	@GetMapping("/booklist")
	public String viewBooklist(Model model) {
		
		model.addAttribute("books", bookRepo.findAll());
		System.out.println(bookRepo.findAll());
		return "member/BookList";
	}
	
	//view whole list
	@GetMapping("/reservelist")
	public String viewreservelist(Model model) {
		
		model.addAttribute("reserves", reservationRepo.findAll());
		System.out.println(reservationRepo.findAll());
		return "lib/ReserveList";
	}
	
	//reserve list
	@GetMapping("rlist")
	public String viewRlist(Model model) {
		
		model.addAttribute("res", reservationRepo.findBystatus());
		return "lib/Rlist";
	}
	
	//lend list
	@GetMapping("lendlist")
	public String viewLlist(Model model) {
		
		model.addAttribute("lendlist", reservationRepo.findBystatus1());
		return "lib/Llist";
	}
	
	//get chart
	@GetMapping("chart1")
	public String loadChart(Model model){
		List<Reservation> r = (List<Reservation>) reservationRepo.findBystatus();
		int res = r.size();
		model.addAttribute("rs", res);
		
		List<Reservation> l = reservationRepo.findBystatus1();
		int len = l.size();
        model.addAttribute("ln", len);		
		
		return "lib/chart1";
	}
	
	//get check
	@GetMapping("/check")
	public String check(Model model) {
		
		model.addAttribute("c", new Check());
		return "lib/Check";
	}
	
	//get chech1
	@GetMapping("/check1")
	public String check1(Model model) {
		
		model.addAttribute("c", new Check1());
		return "lib/Check1";
	}
	
	//get search form
	@GetMapping("/adsearchmember")
	public String serach2(Model model) {
		
		model.addAttribute("s", new Search1());
		return "/lib/SearchMember";
	}
	
	//search mem
	@PostMapping(path="/adsearch2")
	public String search(@ModelAttribute("s") Search1 s,Model model) {
		
		int id = s.getMemberId();
		List<Member> list = memberRepo.findByMemberId(id);
		
		if(list.isEmpty() != true) {
			model.addAttribute("m", list);
			return "/lib/SM";
		}
		else {
			return "/lib/SearchMember";
		}
	}
	
	//get delete mem
	@GetMapping("/addelete")
	public String addelete(Model model) {
		
		model.addAttribute("d", new Search1());
		return "/lib/NewFile";
	}
	
	//delete mem
	@PostMapping(path="/delete")
	public String delete(@ModelAttribute("d") Search1 s,Model model) {
		
		int id = s.getMemberId();
		List<Member> list = memberRepo.findByMemberId(id);
		
		if(list.isEmpty() != true) {
			memberRepo.delete(id);
			return "/lib/AdminHome";
		}
		else {
			return "/lib/NewFile";
		}
	}
	
	//get lend form
	@PostMapping("/lending")
	public String lending(Model model, @ModelAttribute("c") Check1 c){
		
		int memberId = c.getMemberId();
		String status = c.getStatus();
		
		List<Reservation> arr = reservationRepo.findByIdMemberIdAndStatus(memberId, status);
		
		if(arr.isEmpty() == true) {
			model.addAttribute("l", new Reservation());
			return "lib/Lend";
		}
		else {
			return "/lib/Adminhome";
		}
	}
	
	//get reserve form
	@PostMapping("/reservation")
	public String reserve(Model model, @ModelAttribute("c") Check c) {
		
		int max = 4;
		
		int memberId = c.getMemberId();
		int bookId = c.getBookId();
		
		List<Reservation> arr = reservationRepo.findByIdBookId(bookId);
		List<Reservation> list = reservationRepo.findByIdMemberIdAndIdBookId(memberId, bookId);
		
		if(list.isEmpty() == true) {
			if(arr.size() < max) {
				model.addAttribute("r", new Reservation());
				return "lib/Reservation";
			}
			else {
				return "/lib/Adminhome";
			}
		}
		else {
			return "/lib/Adminhome";
		}
				
	}
	
	//get mem form
	@GetMapping("/adregister")
	public String adreg(Model model) {
		
		model.addAttribute("m", new Member());
		return "lib/RegisterMember";
	}
	
	//get search
	@GetMapping("/adsearchbook")
	public String serach1(Model model) {
		
		model.addAttribute("s", new Search());
		return "/lib/SearchBook";
	}
	
	//get check2 form
	@GetMapping("/adbook")
	public String adbook(Model model) {
		model.addAttribute("s",new Search());
		return "lib/Check2";
	}
	
	//get regbook form
	@PostMapping(path="/addbook")
	public String search(@ModelAttribute("s") Search s,Model model,RedirectAttributes redirectAttributes) {
		
		int id = s.getBookId();
		List<Book> list = bookRepo.findByBookId(id);
		Book book = bookRepo.findOne(id);
		
		if(list.isEmpty() != true) {
			int a = Integer.parseInt(book.getCopyNo());
			if(a<10) {
				model.addAttribute("book",book);
				return "/lib/Edit";
			}
			else {
				return "/lib/Check2";
			}	
		}
		else {
			model.addAttribute("b",new Book());
			return "/lib/RegisterBook";
		}
		
     }
	
	//get edit book form
	@PostMapping("/editbook/{bookId}")
	public String editbook(Model model, @PathVariable("bookId") int bookId,RedirectAttributes redirectAttributes) {
		Book list = bookRepo.findOne(bookId);
		model.addAttribute("book", list);
		return "lib/Edit";
	}
	
	//save member
	@PostMapping(path="/savebylib",produces = "application/json")
	public String saveMemberbyLib(@ModelAttribute("m") Member m) {
		
		memberRepo.save(m);
		return "/lib/Adminhome";
	}
	
	//save book
	@PostMapping(path="/savebook")
	public String saveBook(@ModelAttribute("b") Book b) {
		
		bookRepo.save(b);
		return "/lib/Adminhome";
	}
	
	//save editbook 
	@PostMapping(path="/editbook")
	public String saveeditBook(@ModelAttribute("book") Book book) {
		
		bookRepo.save(book);
		return "/lib/Adminhome";
	}
	
	//submit reserve
	@PostMapping(path="/reserve")
	public String saveReserve(@ModelAttribute("r") Reservation r,Model model) {
		
			reservationRepo.save(r);
			
			int bookId = r.getId().getBookId();
			Book book = bookRepo.findOne(bookId);
	        model.addAttribute("book", book);
	        
	        return "/lib/Edit1";
	}
	
	//edit reserve_no column 
	@PostMapping(path="/editreserve")
	public String editReserve(@ModelAttribute("book") Book book) {
		
		bookRepo.save(book);
		return "/lib/Adminhome";
	}
	
	//lend
	@PostMapping("/lend")
	public String saveLend(@ModelAttribute("l") Reservation l) {
		reservationRepo.save(l);
		return "/lib/Adminhome";
	}
	
	//getlib
	@GetMapping("/reglib")
	public String regLib(Model model) {
		
		model.addAttribute("l", new Librarian());
		return "lib/RegisterLib";
	}
	
	//savelib
	@PostMapping("/saveadlib")
	public String saveLib(@ModelAttribute("l") Librarian l) {
		
		libRepo.save(l);
		return "/lib/Adminhome";
	}
}
