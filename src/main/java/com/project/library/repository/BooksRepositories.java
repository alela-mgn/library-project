package com.project.library.repository;

import com.project.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {
    List<Book> findByTitleStartingWith (String name);
}
