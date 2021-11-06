package com.dejot.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping("/*")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @RequestMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        System.out.println(bookService.getAllBooksByAuthor(author));
        return bookService.getAllBooksByAuthor(author);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/*")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @RequestMapping("/available")
    public List<Book> getAllAvailableBooks() {
       return bookService.getAllAvailableBooks();
    }
}
