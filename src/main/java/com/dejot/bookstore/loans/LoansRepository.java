package com.dejot.bookstore.loans;

import com.dejot.bookstore.book.Book;
import com.dejot.bookstore.client.Client;
import org.springframework.data.repository.CrudRepository;

public interface LoansRepository extends CrudRepository<Loans, Long> {
    public Loans findByBookAndClient(Book book, Client client);
}
