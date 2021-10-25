package com.dejot.bookstore.book;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    public List<Book> findAllByAuthor(String author);

}
