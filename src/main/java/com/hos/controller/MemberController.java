package com.hos.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hos.domain.Book;
import com.hos.domain.File;
import com.hos.domain.Member;
import com.hos.domain.Search;
import com.hos.domain.Search1;
import com.hos.domain.UploadFileResponse;
import com.hos.repo.BookRepo;
import com.hos.repo.MemberRepo;
import com.hos.sevice.FileService;
import com.hos.sevice.MemService;

@Controller
public class MemberController {
	
	public static String uploadDirectory = System.getProperty("This PC");

	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private MemService memService;
	
	@Autowired
	private FileService fileService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@GetMapping("/register")
	public String reg(Model model) {
		model.addAttribute("m", new Member());
		return "member/RegisterMember";
	}
	
	@PostMapping(path = "/editmem/{memberId}",produces = "application/json")
	public String editMem(Model model,@PathVariable("memberId") int memberId) {
		
		Member mem = memberRepo.findOne(memberId);
		model.addAttribute("m", mem);
		return "member/Edit";
	}
	
	@PostMapping(path="/editmem",produces = "application/json")
	public String editMemberbyMem(@ModelAttribute("m") Member m) {
		
		memberRepo.save(m);
		return "/member/Memhome";
	}
	
	
	@GetMapping("/memhome")
	public String memHome(Model model) {
		
		List<Member> list1 = (List<Member>) memberRepo.findAll();
		int count1 = list1.size();
		model.addAttribute("count1", count1);
		
		List<Book> list2 = (List<Book>) bookRepo.findAll();
		int count2 = list2.size();
		model.addAttribute("count2", count2);
		
		return "/member/Memhome";
	}
	
	@GetMapping("/searchbook")
	public String serach1(Model model) {
		
		model.addAttribute("s", new Search());
		return "/member/SearchBook";
	}
	
	@PostMapping(path="/search1")
	public String search(@ModelAttribute("s") Search s,Model model,RedirectAttributes redirectAttributes) {
		
		int id = s.getBookId();
		List<Book> list = bookRepo.findByBookId(id);
		
		if(list.isEmpty() != true) {
			model.addAttribute("m", list);
			return "/member/SB";
		}
		else {
			return "/member/SearchBook";
		}
		
 }
	
	@GetMapping("/searchmember")
	public String serach2(Model model) {
		
		model.addAttribute("s", new Search1());
		return "/lib/SearchMember";
	}
	
	@PostMapping(path="/savebymem",produces = "application/json")
	public String saveMemberbyMem(@ModelAttribute("m") Member m) {
		
		String fname = m.getfName();
		String lname = m.getlName();
		
		List<Member> mem = memberRepo.findByFNameAndLName(fname, lname);
		if(mem.isEmpty() == true) {
			memberRepo.save(m);
			return "/member/Memhome";
		}
		else {
			return "member/RegisterMember";
		}
	}
	
	@GetMapping(path="/readfiledata")
	public String sendFileToDB(Model model) {
	
		memService.saveMemberData();
		model.addAttribute("msg", "Successful!");
		
		return "/Message";
	}
	
	@GetMapping(path="/uploadfile")
	public String uploadFile(Model model) {
		
		model.addAttribute("file", new File());
		return "member/Upload";
	}
	
	@PostMapping(path="/upload")
	public String upload(Model model,@RequestParam("file") MultipartFile file) {
		
		File f = fileService.storefile(file);
		String downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadfile").path(f.getName()).toUriString();
		
		model.addAttribute("file", new UploadFileResponse(f.getName(), downloadURI,file.getContentType(), file.getSize()));
		return "member/Uploadmore";
		
	}
	/*
	@PostMapping(path="/uploadmultiple")
	public List<String> uploadMultiple(Model model,@RequestParam("files") MultipartFile[] files) {
		
		return Arrays.asList(files).stream().map(file -> upload(model, file)).collect(Collectors.toList());
	}*/
	
	@PostMapping(path="/uploadmultiple")
	public String uploadMultiple(Model model,@RequestParam("files") MultipartFile[] files){
		
		Arrays.asList(files).stream().map(file -> upload(model, file)).collect(Collectors.toList());
		return "member/Uploadmore";
	}
	
	@GetMapping("/downloadFile/{id}")
	@ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable int id){
		
		File f = fileService.returnFile(id);
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(f.getType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + f.getName() + "\"").body(new ByteArrayResource(f.getData()));
	}
}
