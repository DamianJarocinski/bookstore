package com.dejot.bookstore.loans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoansController {

    @Autowired
    private LoansService loansService;

    @RequestMapping("/*")
    public List<Loans> getAllLoans() {
        return loansService.getAllLoans();
    }

    @RequestMapping("/{id}")
    public Loans getLoan(@PathVariable Long id) {
        return loansService.getLoan(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/*")
    public void addLoans(@RequestBody Loans loans) {
        loansService.addLoans(loans);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateLoans(@PathVariable Long id, @RequestBody Loans loans) {
        loansService.updateLoans(id, loans);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteLoans(@PathVariable Long id) {
        loansService.deleteLoans(id);
    }


    @RequestMapping("/loan/{bookId}/{clientId}")
    public Loans findByBookIdAndClientId(@PathVariable Long bookId, @PathVariable Long clientId) {
        return loansService.findByBookIdAndClientId(bookId, clientId);
    }

    @RequestMapping("/client/{clientId}")
    public List<Loans> getAllLoansByClientId(@PathVariable Long clientId) {
        return loansService.getAllLoansByClientId(clientId);
    }


    //TODO: dodac mozliwosc podania dateofreturn z body
    @RequestMapping(method = RequestMethod.PUT, value = "/return")
    public void returnBook(@RequestBody ObjectBookAndClientHolder objectBookAndClientHolder) {
        loansService.returnBook(objectBookAndClientHolder);
    }
}

