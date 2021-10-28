package com.dejot.bookstore.loans;

import com.dejot.bookstore.book.Book;
import com.dejot.bookstore.client.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoansRepository extends CrudRepository<Loans, Long> {
    Loans findByBookAndClient(Book book, Client client);
    List<Loans> findAllByClientId(Long clientId);

    Loans findByBookIdAndClientId(Long bookId, Long clientId);
}
