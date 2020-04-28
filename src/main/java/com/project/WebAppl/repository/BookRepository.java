package com.project.WebAppl.repository;

import com.project.WebAppl.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
//    List<Book> findByBook(String Author);

}
