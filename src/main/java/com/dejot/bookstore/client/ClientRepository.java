package com.dejot.bookstore.client;

import com.dejot.bookstore.book.Book;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
