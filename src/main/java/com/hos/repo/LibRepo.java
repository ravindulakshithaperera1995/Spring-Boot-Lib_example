package com.hos.repo;

import org.springframework.data.repository.CrudRepository;

import com.hos.domain.Librarian;

public interface LibRepo extends CrudRepository<Librarian,Integer>{

}
