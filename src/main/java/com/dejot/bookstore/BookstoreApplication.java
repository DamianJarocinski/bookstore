package com.dejot.bookstore;

import com.dejot.bookstore.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.util.Calendar;

@SpringBootApplication
public class BookstoreApplication {



	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);





	}


}
