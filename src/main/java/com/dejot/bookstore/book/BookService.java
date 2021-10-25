package com.dejot.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    };

    public Book getBook(Long id) {
        return bookRepository.findById(id).get();
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void updateBook(Long id, Book book){
        Book tempBook = new Book();
        tempBook = bookRepository.findById(id).get();
        tempBook.setAuthor(book.getAuthor());
        tempBook.setTitle(book.getTitle());
        tempBook.setDateOfRelease(book.getDateOfRelease());
        tempBook.setAvailable(book.isAvailable());
        bookRepository.save(tempBook);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> getAllBooksByAuthor(String author){
        List<Book> allBooksByAuthor = new ArrayList<>();
        bookRepository.findAllByAuthor(author).forEach((Object book) -> allBooksByAuthor.add((Book)book));
        return allBooksByAuthor;
    }


}
