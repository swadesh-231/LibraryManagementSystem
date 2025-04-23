package com.project.librarymanagement.repository;

import com.project.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByTitle(String title);
}
