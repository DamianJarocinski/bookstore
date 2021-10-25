package com.dejot.bookstore.loans;

import com.dejot.bookstore.book.Book;
import com.dejot.bookstore.book.BookService;
import com.dejot.bookstore.client.Client;
import com.dejot.bookstore.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class LoansService {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BookService bookService;

    public List<Loans> getAllLoans() {
        List<Loans> allLoans = new ArrayList<Loans>();
        loansRepository.findAll().forEach(allLoans::add);
        return allLoans;
    }

    public Loans getLoan(Long id) {
        return loansRepository.findById(id).get();
    }

    public void addLoans(Loans loans) {
        if (LoansService.isLoanAvailable(loans.getBook(), loans.getClient()) == false) {
            System.out.println("Loan not available.");
        } else {
            //aktualizuje liczbe wypozyczen klienta
            Client tempClient = loans.getClient();
            tempClient.setNumberOfBorrowedBooks(tempClient.getNumberOfBorrowedBooks() + 1);
            clientService.updateClient(tempClient.getId(), tempClient);

           //aktualizuje status ksiazki
            Book tempBook = loans.getBook();
            tempBook.setAvailable(false);
            bookService.updateBook(tempBook.getId(), tempBook);

            loansRepository.save(loans);
        }
    }

    public void updateLoans(Long id, Loans loans) {
        Loans tempLoans = new Loans();
        tempLoans = loansRepository.findById(id).get();
        tempLoans.setBook(loans.getBook());
        tempLoans.setClient(loans.getClient());
        tempLoans.setDateOfLoan(loans.getDateOfLoan());
        tempLoans.setDateOfReturn(loans.getDateOfReturn());
        tempLoans.setDateToReturn(loans.getDateToReturn());
        loansRepository.save(tempLoans);
    }

    public void deleteLoans(Long id) {
        loansRepository.deleteById(id);
    }


    public Loans findByBookAndClient(Book book, Client client) {
        return loansRepository.findByBookAndClient(book, client);
    }

    public static boolean isLoanAvailable(Book book, Client client) {
        if (book.isAvailable() == true && client.getNumberOfBorrowedBooks() < 3) {
            return true;
        } else {
            return false;
        }
    }

    public void returnBook(ObjectClientAndBookHolder objectClientAndBookHolder) {
        Loans tempLoans = loansRepository.findByBookAndClient(objectClientAndBookHolder.book, objectClientAndBookHolder.client);
        Calendar date = Calendar.getInstance();
        tempLoans.setDateOfReturn(date);
        loansRepository.save(tempLoans);

        //aktualizuje liczbe wypozyczen klienta
        Client tempClient = objectClientAndBookHolder.client;
        tempClient.setNumberOfBorrowedBooks(tempClient.getNumberOfBorrowedBooks() - 1);
        clientService.updateClient(tempClient.getId(), tempClient);


        //aktualizuje status ksiazki
        Book tempBook = objectClientAndBookHolder.book;
        tempBook.setAvailable(true);
        bookService.updateBook(tempBook.getId(), tempBook);

    }



}
