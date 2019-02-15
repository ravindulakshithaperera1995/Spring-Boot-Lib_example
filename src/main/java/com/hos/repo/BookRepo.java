package com.hos.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hos.domain.Book;

public interface BookRepo extends CrudRepository<Book,Integer>{

	List<Book> findByBookId(int bookId);
}
