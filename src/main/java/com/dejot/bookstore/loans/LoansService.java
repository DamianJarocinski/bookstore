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
        List<Loans> allLoans = new ArrayList<>();
        loansRepository.findAll().forEach(allLoans::add);
        return allLoans;
    }

    public Loans getLoan(Long id) {
        return loansRepository.findById(id).get();
    }

    public void addLoans(Loans loans) {
        if (!LoansService.isLoanAvailable(loans.getBook(), loans.getClient())) {
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
        if (loansRepository.findById(id).isPresent()) {
            tempLoans = loansRepository.findById(id).get();
            tempLoans.setBook(loans.getBook());
            tempLoans.setClient(loans.getClient());
            tempLoans.setDateOfLoan(loans.getDateOfLoan());
            tempLoans.setDateOfReturn(loans.getDateOfReturn());
            tempLoans.setDateToReturn(loans.getDateToReturn());
            loansRepository.save(tempLoans);
        }
        else {
            System.out.println("Podano nieprawidłowe wartości. Aktualizacja danych nie udana.");
        }

    }

    public void deleteLoans(Long id) {
        loansRepository.deleteById(id);
    }


    public Loans findByBookIdAndClientId(Long bookId, Long clientId) {
        return loansRepository.findByBookIdAndClientId(bookId, clientId);
    }

    public static boolean isLoanAvailable(Book book, Client client) {
        return book.isAvailable() && client.getNumberOfBorrowedBooks() < 3;
    }

    public void returnBook(ObjectBookAndClientHolder objectBookAndClientHolder) {
        Loans tempLoans = loansRepository.findByBookAndClient(objectBookAndClientHolder.book, objectBookAndClientHolder.client);
        Calendar date = Calendar.getInstance();
        tempLoans.setDateOfReturn(date);
        loansRepository.save(tempLoans);

        //aktualizuje liczbe wypozyczen klienta (odejmuje)
        Client tempClient = objectBookAndClientHolder.client;
        tempClient.setNumberOfBorrowedBooks(tempClient.getNumberOfBorrowedBooks() - 1);
        clientService.updateClient(tempClient.getId(), tempClient);


        //aktualizuje status ksiazki (na dostepny)
        Book tempBook = objectBookAndClientHolder.book;
        tempBook.setAvailable(true);
        bookService.updateBook(tempBook.getId(), tempBook);

        //TODO: obsluga przedawnien
        checkIfReturnIsOnTime(tempLoans);

    }

        public boolean checkIfReturnIsOnTime(Loans loans) {
            return loans.getDateToReturn().compareTo(loans.getDateOfReturn()) < 1;
        }


    public List<Loans> getAllLoansByClientId(Long clientId) {
        List<Loans> allLoansByClientId = new ArrayList<>();
        loansRepository.findAllByClientId(clientId).forEach(allLoansByClientId::add);
        return allLoansByClientId;
    }
}
