package com.hos.repo;

import org.springframework.data.repository.CrudRepository;

import com.hos.domain.File;

public interface FileRepo extends CrudRepository<File,Integer>{

}
