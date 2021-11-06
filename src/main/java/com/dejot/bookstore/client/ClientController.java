package com.dejot.bookstore.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping("/*")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @RequestMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/*")
    public void addClient(@RequestBody Client client){
        clientService.addClient(client);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateClient(@PathVariable Long id, @RequestBody Client client) {
        clientService.updateClient(id, client);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
