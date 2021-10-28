package com.dejot.bookstore.loans;

import com.dejot.bookstore.book.Book;
import com.dejot.bookstore.client.Client;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loansId", updatable = false, nullable = false, unique = true)
    private Long id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Client client;
    @Column(nullable = false)
    private Calendar dateOfLoan;
    @Column(nullable = true)
    private Calendar dateOfReturn;
    @Column(nullable = false)
    private Calendar dateToReturn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Calendar getDateOfLoan() {
        return dateOfLoan;
    }

    public void setDateOfLoan(Calendar dateOfLoan) {
        this.dateOfLoan = dateOfLoan;
    }

    public Calendar getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Calendar dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Calendar getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(Calendar dateToReturn) {
        this.dateToReturn = dateToReturn;
    }


    public Loans() {}

    public Loans(Long id, Book book, Client client, Calendar dateOfLoan, Calendar dateToReturn) {

            this.id = id;
            this.book = book;
            this.client = client;
            this.dateOfLoan = dateOfLoan;
            this.dateToReturn = dateToReturn;

    }


}
