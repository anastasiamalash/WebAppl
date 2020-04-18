package com.project.WebAppl.repository;

import com.project.WebAppl.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
