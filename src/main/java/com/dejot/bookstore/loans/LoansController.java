package com.dejot.bookstore.loans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoansService loansService;

    @RequestMapping("/loans")
    public List<Loans> getAllLoans() {
        return loansService.getAllLoans();
    }

    @RequestMapping("/loans/{id}")
    public Loans getLoan(@PathVariable Long id) {
        return loansService.getLoan(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/loans")
    public void addLoans(@RequestBody Loans loans) {
        loansService.addLoans(loans);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/loans/{id}")
    public void updateLoans(@PathVariable Long id, @RequestBody Loans loans) {
        loansService.updateLoans(id, loans);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/loans/{id}")
    public void deleteLoans(@PathVariable Long id) {
        loansService.deleteLoans(id);
    }


    @RequestMapping("/loans/find/{bookId}/{clientId}")
    public Loans findByBookIdAndClientId(@PathVariable Long bookId, @PathVariable Long clientId) {
        return loansService.findByBookIdAndClientId(bookId, clientId);
    }

    @RequestMapping("/loans/client/{clientId}")
    public List<Loans> getAllLoansByClientId(@PathVariable Long clientId) {
        return loansService.getAllLoansByClientId(clientId);
    }


    //TODO: dodac mozliwosc podania dateofreturn z body
    @RequestMapping(method = RequestMethod.PUT, value = "/loans/return")
    public void returnBook(@RequestBody ObjectBookAndClientHolder objectBookAndClientHolder) {
        loansService.returnBook(objectBookAndClientHolder);
    }
}

