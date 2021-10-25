package com.dejot.bookstore.book;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId", updatable = false, nullable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = true)
    private Calendar dateOfRelease;
    @Column(nullable = false)
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Calendar getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Calendar dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Book() {}

    public Book(long id, String title, String author, Calendar dateOfRelease) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.dateOfRelease = dateOfRelease;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, Calendar dateOfRelease) {
        this.title = title;
        this.author = author;
        this.dateOfRelease = dateOfRelease;
    }

    public Book(Long id, String title, String author, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", dateOfRelease=" + dateOfRelease +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
