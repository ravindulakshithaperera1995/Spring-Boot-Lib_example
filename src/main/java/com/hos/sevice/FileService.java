package com.hos.sevice;

import java.io.IOException;
import java.nio.file.FileSystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hos.domain.File;
import com.hos.repo.FileRepo;


@Service
public class FileService {

	@Autowired
	private FileRepo fileRepo;
	
	public File storefile(MultipartFile file) {
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(filename.contains("..")) {
				throw new FileSystemException("Sorry! Filename contains invalid path sequence " + filename);
			}
			File f = new File(filename,file.getContentType(),file.getBytes());
			return fileRepo.save(f);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public File returnFile(int id) {
		
		return fileRepo.findOne(id);
	}
	
}
