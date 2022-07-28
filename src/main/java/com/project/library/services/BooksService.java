package com.project.library.services;

import com.project.library.models.Book;
import com.project.library.models.Person;
import com.project.library.repository.BooksRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepositories booksRepositories;

    @Autowired
    public BooksService(BooksRepositories booksRepositories){
        this.booksRepositories = booksRepositories;
    };

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return booksRepositories.findAll(Sort.by("year"));
        } else {
            return booksRepositories.findAll();
        }
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear) {
            return booksRepositories.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        } else {
            return booksRepositories.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepositories.findById(id);
        return foundBook.orElse(null);
    }

    public List<Book> searchByTitle(String name) {
        return booksRepositories.findByTitleStartingWith(name);
    }

    @Transactional
    public void save(Book book) {
        booksRepositories.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Book bookToBeUpdated = booksRepositories.findById(id).get();

        book.setId(id);
        book.setOwner(bookToBeUpdated.getOwner());
        booksRepositories.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepositories.deleteById(id);
    }

    public Person getBookOwner(int id) {
        return booksRepositories.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void release(int id) {
        booksRepositories.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakenAt(null);
                }
        );
    }

    @Transactional
    public void assign(int id, Person person) {
        booksRepositories.findById(id).ifPresent(
                book -> {
                    book.setOwner(person);
                    book.setTakenAt(new Date());
                }
        );
    }
}
