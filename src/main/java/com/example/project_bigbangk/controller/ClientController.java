// Created by vip
// Creation date 02/12/2021

package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> getClientsHandler() {
        return clientService.getAllClients();
    }

    //TODO de volgende methode klopt niet -> aanpassen!

//    @GetMapping("/clients/{email}")
//    public ResponseEntity<Client> getClientByEmailHandler(@PathVariable String email) {
//        Client client = clientService.getClientByEmail(email);
//        if (client == null) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.ok(client);
//        }
//    }

    @PostMapping("/clients")
    public void createClient(@RequestBody Client client) {
        clientService.saveClient(client);
    }

    @PutMapping("/clients/{email}")
    public String updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @GetMapping("/clients/search")
    public List<Client> findClients(@RequestParam String lastName) {
        return clientService.findClientByLastName(lastName);
    }

}